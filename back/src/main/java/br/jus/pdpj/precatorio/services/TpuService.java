package br.jus.pdpj.precatorio.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.jus.pdpj.starter.base.estruturantes.services.TpuAbstractService;

/**
 * Classe de exemplo estendendo a classe responsável para chamar o serviço de consulta da TPU.
 * É necessário apenas injetar o valor da propriedade <code>gatewayUrl</code> no construtor. 
 * @author adriano.silva
 */
@Service
public class TpuService extends TpuAbstractService {

	protected TpuService(@Value("${pdpj.gatewayUrl}") String gatewayUrl) {
		super(gatewayUrl);
	}
}
