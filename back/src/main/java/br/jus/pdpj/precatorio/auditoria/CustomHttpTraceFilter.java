package br.jus.pdpj.precatorio.auditoria;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import br.jus.pdpj.commons.utils.RequestUtils;

/**
 * Filtro para realizar a auditoria das requisições feitas.
 * Baseado no código encontrado em: https://turkogluc.com/spring-monitor-rest-api-calls/
 * @author adriano.silva
 *
 */
@Component
public class CustomHttpTraceFilter extends OncePerRequestFilter {

    private ContentTrace contentTrace;
    
    private final ContentTraceEventHandler contentTraceEventHandler;
    private final String[] urlsNaoLogar;
    
    @Autowired
    public CustomHttpTraceFilter(
    		ContentTraceEventHandler contentTraceEventHandler, @Value("${auditoria.naoLogar}") String[] urlsNaoLogar) {
    	this.contentTraceEventHandler = contentTraceEventHandler;
    	this.urlsNaoLogar = urlsNaoLogar;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        contentTrace = new ContentTrace();
        contentTrace.setTimestamp(LocalDateTime.now());
        try {
            filterChain.doFilter(requestWrapper, responseWrapper);
        } finally {
            afterRequest(requestWrapper, responseWrapper);
        }
    }

    @SuppressWarnings("deprecation")
	private void afterRequest(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper) throws IOException {

    	if (!auditavel(requestWrapper, responseWrapper)) {
    		return;
    	}
    	
        //From Request Headers
        contentTrace.setTimeTaken(System.currentTimeMillis() - contentTrace.getTimestamp().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        contentTrace.setMethod(requestWrapper.getMethod());
        contentTrace.setRemoteAddress(RequestUtils.getIp(requestWrapper));
        String requestString = requestWrapper.getRequestURI();
        if(requestWrapper.getQueryString() != null){
            requestString+="?"+requestWrapper.getQueryString();
        }
        contentTrace.setUri(requestString);
        contentTrace.setHost(requestWrapper.getHeader("host"));
        contentTrace.setAuthorization(requestWrapper.getHeader("authorization"));
        contentTrace.setUserAgent(requestWrapper.getHeader("user-agent"));
        contentTrace.setReferer(requestWrapper.getHeader("referer"));
        contentTrace.setRequestBody(getRequestPayload(requestWrapper));
        
        //From keycloak 
        contentTrace.setUsername(getUserName(requestWrapper.getSession(false)));

        //From Response Headers
        contentTrace.setStatus(responseWrapper.getStatusCode());
        contentTrace.setResponseBody(getResponsePayload(responseWrapper));

        //Important to copy the original response body, because it is removed.
        responseWrapper.copyBodyToResponse();

        // publish event
        contentTraceEventHandler.publishContentTrace(contentTrace);
    }
    
    private String getUserName(HttpSession session) {
    	if (session!=null && session.getAttribute("SPRING_SECURITY_CONTEXT")!=null) {
    		SecurityContextImpl sci = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
    		KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) sci.getAuthentication();
    		return token.getPrincipal().toString();
    	}
    	return null;
    }
    
    private boolean auditavel(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper) throws IOException {
    	String uri = requestWrapper.getRequestURI();
    	String referer = requestWrapper.getHeader("referer");
    	if (Arrays.stream(urlsNaoLogar).anyMatch(urlNaoLogar -> 
    			uri.contains(urlNaoLogar) || (referer!=null && referer.contains(urlNaoLogar)))) {
    		responseWrapper.copyBodyToResponse();
    		return false;
    	}
    	return true;
    }
    
    private String getRequestPayload(HttpServletRequest request) {
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                int length = buf.length;
                try {
                    return new String(buf, 0, length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException ex) {
                    return "[unknown]";
                }
            }
        }
        return "";
    }

    private String getResponsePayload(ContentCachingResponseWrapper wrappedResponse) {
        try {
            if (wrappedResponse.getContentSize() <= 0) {
                return null;
            }
            return new String(wrappedResponse.getContentAsByteArray(), 0,
                    wrappedResponse.getContentSize(),
                    wrappedResponse.getCharacterEncoding());
        } catch (UnsupportedEncodingException e) {
            logger.error("Could not read cached response body: " + e.getMessage());
            return null;
        }
    }
}
