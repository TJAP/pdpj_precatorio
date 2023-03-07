package br.jus.pdpj.precatorio.auditoria;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.envers.RevisionListener;
import org.springframework.beans.factory.annotation.Autowired;

import br.jus.pdpj.commons.utils.KeycloakUtil;
import br.jus.pdpj.commons.utils.RequestUtils;
import br.jus.pdpj.precatorio.models.entities.RevisionInfo;

public class EnversListener implements RevisionListener {
	
	private final HttpServletRequest request;
	
	@Autowired
	public EnversListener(HttpServletRequest request) {
		this.request = request;
	}
	
    public void newRevision(Object revisionEntity) {
    	RevisionInfo revision = (RevisionInfo) revisionEntity;
    	revision.setIp(RequestUtils.getIp(request));
    	revision.setUseragent(request.getHeader("user-agent"));
    	revision.setUsername(KeycloakUtil.getLoginUsuarioLogado());
    }
    
}