package br.jus.pdpj.precatorio.models.dtos;

import java.io.Serializable;

public class PdpjInfoDTO implements Serializable {
	
    private static final long serialVersionUID = 1L;

	private String nomeServico;
	private String descricao;
	private String iconePequenoUrl;
	private String iconeGrandeUrl;
	private String swaggerUrl;
    private String frontendUrl;
	private String documentacaoUsuarioUrl;
	private String documentacaoTecnicaUrl;
	
	public String getNomeServico() {
		return nomeServico;
	}

	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIconePequenoUrl() {
		return iconePequenoUrl;
	}

	public void setIconePequenoUrl(String iconePequenoUrl) {
		this.iconePequenoUrl = iconePequenoUrl;
	}

	public String getIconeGrandeUrl() {
		return iconeGrandeUrl;
	}

	public void setIconeGrandeUrl(String iconeGrandeUrl) {
		this.iconeGrandeUrl = iconeGrandeUrl;
	}
	
	public String getSwaggerUrl() {
		return swaggerUrl;
	}

	public void setSwaggerUrl(String swaggerUrl) {
		this.swaggerUrl = swaggerUrl;
	}

	public String getDocumentacaoUsuarioUrl() {
		return documentacaoUsuarioUrl;
	}

	public void setDocumentacaoUsuarioUrl(String documentacaoUsuarioUrl) {
		this.documentacaoUsuarioUrl = documentacaoUsuarioUrl;
	}

	public String getDocumentacaoTecnicaUrl() {
		return documentacaoTecnicaUrl;
	}

	public void setDocumentacaoTecnicaUrl(String documentacaoTecnicaUrl) {
		this.documentacaoTecnicaUrl = documentacaoTecnicaUrl;
	}

    public String getFrontendUrl() {
        return this.frontendUrl;
    }

    public void setFrontendUrl(String frontendUrl) {
        this.frontendUrl = frontendUrl;
    }
	
}
