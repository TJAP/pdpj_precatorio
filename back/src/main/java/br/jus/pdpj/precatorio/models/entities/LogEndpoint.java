package br.jus.pdpj.precatorio.models.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = LogEndpoint.TABLE_NAME, schema="precatorio")
public class LogEndpoint implements Serializable {

	public static final String TABLE_NAME = "log_endpoint";
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_" + TABLE_NAME)
	@SequenceGenerator(name = "gen_" + TABLE_NAME, sequenceName = "sq_" + TABLE_NAME, allocationSize = 1)
	private Long id;
	
	@Column(name = "timestamp")
	public LocalDateTime timestamp;
	
	@Column(name = "time_taken")
    public long timeTaken;
	
	@Column(name = "status")
    public int status;
	
	@Column(name = "method")
    public String method;
	
	@Column(name = "uri")
    public String uri;
	
	@Column(name = "host")
    public String host;
	
    @Column(name = "user_agent")
    public String userAgent;
    
    @Column(name = "referer")
    public String referer;
    
    @Column(name = "username")
    public String username;
    
    @Column(name = "remote_address")
    public String remoteAddress;
    
    @Column(name = "request_body")
    public String requestBody;
    
    @Column(name = "response_body")
    public String responseBody;
    
    @Override
	public String toString() {
		return "LogEndpoint [" + uri + "]";
	}
    
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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