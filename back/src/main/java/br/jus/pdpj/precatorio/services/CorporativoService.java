package br.jus.pdpj.precatorio.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.jus.pdpj.starter.base.estruturantes.services.CorporativoAbstractService;

/**
 * Classe de exemplo estendendo a classe responsável para chamar o serviço do CNJ Corporativo.
 * É necessário apenas injetar o valor da propriedade <code>gatewayUrl</code> no construtor. 
 * @author adriano.silva
 */
@Service
public class CorporativoService extends CorporativoAbstractService {

	protected CorporativoService(@Value("${pdpj.gatewayUrl}") String gatewayUrl) {
		super(gatewayUrl);
	}
}
