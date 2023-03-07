package br.jus.pdpj.precatorio.auditoria;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentTrace implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private LocalDateTime timestamp;
	private long timeTaken;
	private int status;
	private String method;
	private String uri;
	private String host;
	private String authorization;
	private String userAgent;
	private String referer;
	private String username;
	private String remoteAddress;
	private String requestBody;
	private String responseBody;
    
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public long getTimeTaken() {
		return timeTaken;
	}
	public void setTimeTaken(long timeTaken) {
		this.timeTaken = timeTaken;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getAuthorization() {
		return authorization;
	}
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRemoteAddress() {
		return remoteAddress;
	}
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}
	public String getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}
	public String getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
}