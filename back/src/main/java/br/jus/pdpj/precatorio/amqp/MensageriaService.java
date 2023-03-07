package br.jus.pdpj.precatorio.amqp;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.jus.pdpj.commons.builders.DomainEventMessageBuilder;
import br.jus.pdpj.commons.exceptions.DomainEventMessageBuilderException;
import br.jus.pdpj.commons.models.dtos.amqp.DomainEventMessage;
import br.jus.pdpj.commons.models.enums.InstanciaJusticaEnum;
import br.jus.pdpj.commons.models.enums.NivelSigiloEnum;
import br.jus.pdpj.commons.models.enums.TribunalEnum;
import br.jus.pdpj.commons.models.vo.PessoaSimples;
import br.jus.pdpj.commons.utils.KeycloakUtil;
import br.jus.pdpj.precatorio.exceptions.ErroInternoException;
import br.jus.pdpj.starter.configurations.properties.PDPJProperties;

@Service
public class MensageriaService {

	private static final String NOME_SERVICO = "precatorio";
	
	private final RabbitTemplate rabbitTemplate;
	private final ObjectMapper mapper;
	private final PDPJProperties pdpjProperties;

    @Autowired
    public MensageriaService (RabbitTemplate rabbitTemplate, ObjectMapper mapper, PDPJProperties pdpjProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
        this.pdpjProperties = pdpjProperties;
    }
	
	/**
	 * Encaminha uma mensagem para o serviço de mensageria.
	 * @param payload O payload da mensagem.
     * @param numeroProcesso O número do processo judicial vinculado a esta mensagem.
	 * @param eventName <code>String</code> que corresponde ao nome do Evento que está sendo lançado. Esse é o nome
	 * que será registrado no serviço de webhooks/notificação da PDPJ.
	 */
	public void sendMessage(Object payload, String numeroProcesso, String eventName) {
		DomainEventMessage message = this.buildDomainEventMessage(payload, numeroProcesso, eventName);
		try {
			String json = this.mapper.writeValueAsString(message);
			this.rabbitTemplate.convertAndSend(message.getRoutingKey(), json);
		}
		catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new ErroInternoException("Erro ao tentar converter a mensagem em uma String em formato JSON. Mensagem do erro: "+e.getMessage(), e);
		}
	}
	
	/**
	 * Monta um objeto <code>DomainEventMessage</code>, esperado pelo serviço de mensageria da PDPJ.
	 * Os seguintes métodos são utilizados para montar a <code>routing key</code>:
	 * <ul>
	 * 	<li>withAppName - representa o nome do serviço</li>
	 *  <li>withInstanciaTribunalAcao - de onde se extrai o JTR e o GRAU</li>
	 *  <li>withEventName - o nome do evento</li>
	 * </ul>
	 * @param payload o objeto a ser enviado na mensagem.
	 * @param numeroProcesso o número do processo judicial que se relaciona com esta mensagem.
	 * @param eventName o nome do evento. Deve Estar no passado. Ex: 'OrdemProtocolada'.
	 * @return Um objeto <code>DomainEventMessage</code>.
	 */
	private DomainEventMessage buildDomainEventMessage(Object payload, String numeroProcesso, String eventName) {
        String chaveMensageria = "uma chave qualquer";
		TribunalEnum tribunal = TribunalEnum.findByJTR(this.pdpjProperties.getJtr());
		try {
			return DomainEventMessageBuilder
					.instance(chaveMensageria)
					.withAppName(NOME_SERVICO) // Nome do serviço
					.withAppVersion("1")
					.withNumeroUnicoProcesso(numeroProcesso)
					.withNivelSigiloEvento(NivelSigiloEnum.PUBLICO)
					.withInstanciaTribunalAcao(tribunal, InstanciaJusticaEnum.NAO_SE_APLICA, null) // JTR e GRAU
					.withAutor(new PessoaSimples(UUID.randomUUID(), KeycloakUtil.getLoginUsuarioLogado(), KeycloakUtil.getNomeUsuarioLogado()))
					.withPayload(payload)
					.withEventName(eventName) // Nome do evento
					.build();
		}
		catch (JsonProcessingException | DomainEventMessageBuilderException e) {
			e.printStackTrace();
			throw new ErroInternoException("Erro ao tentar enviar mensagem ao serviço de mensageria da PDPJ. Mensagem do erro: "+e.getMessage(), e);
		}
	}
	
}

