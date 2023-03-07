
package br.jus.pdpj.precatorio.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.pdpj.precatorio.models.dtos.PdpjInfoDTO;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/pdpj")
@ApiIgnore
public class MarketplaceRestController {
    
    @Value("${pdpj.nomeServico}")
	private String nomeServico;
	
	@Value("${pdpj.descricao}")
	private String descricao;
	
	@Value("${pdpj.iconePequenoUrl}")
	private String iconePequenoUrl;
	
	@Value("${pdpj.iconeGrandeUrl}")
	private String iconeGrandeUrl;
	
	@Value("${pdpj.swaggerUrl}")
	private String swaggerUrl;

	@Value("${pdpj.documentacaoUsuarioUrl}")
	private String documentacaoUsuarioUrl;
	
	@Value("${pdpj.documentacaoTecnicaUrl}")
	private String documentacaoTecnicaUrl;
	
	@Value("${eureka.instance.homePageUrl}")
	private String homePageUrl;
	
	@ApiOperation(value="Retorna as informações da aplicacação necessárias à integração com a Plataforma Digital do Poder Judiciário - PDPJ", hidden = true)
	@GetMapping(path="/info")
	public PdpjInfoDTO obterInfo() {
		PdpjInfoDTO info = new PdpjInfoDTO();
		info.setNomeServico(nomeServico);
		info.setDescricao(descricao);
		info.setIconePequenoUrl(homePageUrl+iconePequenoUrl);
		info.setIconeGrandeUrl(homePageUrl+iconeGrandeUrl);
		info.setSwaggerUrl(swaggerUrl);
		info.setFrontendUrl(homePageUrl);
		info.setDocumentacaoTecnicaUrl(documentacaoTecnicaUrl);
		info.setDocumentacaoUsuarioUrl(documentacaoUsuarioUrl);
		return info;
	}
}
