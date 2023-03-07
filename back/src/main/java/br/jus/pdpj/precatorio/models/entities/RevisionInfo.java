package br.jus.pdpj.precatorio.models.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import br.jus.pdpj.precatorio.auditoria.EnversListener;;

@Entity
@RevisionEntity(EnversListener.class)
@Table(name="revinfo", schema="audit")
public class RevisionInfo extends DefaultRevisionEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String username;
    private String ip;
    private String useragent;
    
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getUseragent() {
		return useragent;
	}
	
	public void setUseragent(String useragent) {
		this.useragent = useragent;
	}
}
