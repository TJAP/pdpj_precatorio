package br.jus.pdpj.precatorio.amqp;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.pdpj.commons.models.dtos.amqp.DomainEventMessage;

/**
 * Classe que consome uma mensagem do serviço de mensageria.
 * Pode consumir mensagens deste serviço, ou de outros serviços da PDPJ.
 */
@Component
public class ReferenciaConsumer {

	private static final Logger logger = LoggerFactory.getLogger(ReferenciaConsumer.class);

	public static final String SERVICE_DEFAULT_QUEUE = "pdpj.precatorio-fila";
	public static final String DEFAULT_EXCHANGE = "pdpj.exchange";
	
    /**
     * <p>A <em>routing key</em> de uma mensagem segue o formato 'JTR.GRAU.NOME-SERVICO.NOME-EVENTO'.
     * Ex: 820.0.SISBAJUD.OrdemProtocolada.</p>
     * <p>Admite a utilização de <em>patterns</em> para filtrar pelo <em>routing key</em>.
     * Assim, para consumir todas mensagens lançadas pelo Sisbajud, por exemplo, a <em>routing
     * key</em> seria '#.#.SISBAJUD.#'
     * </p>
     */
	public static final String ROUTING_KEY_PATTERN = "#";

	private final ObjectMapper mapper;

    @Autowired
    public ReferenciaConsumer(ObjectMapper mapper) {
        this.mapper = mapper;
    }
	
	@RabbitListener(
		bindings = @QueueBinding(
			value = @Queue(value = SERVICE_DEFAULT_QUEUE, durable = "true", autoDelete = "false", exclusive = "false"), 
			exchange = @Exchange(value = DEFAULT_EXCHANGE, type = ExchangeTypes.TOPIC), 
			key = {ROUTING_KEY_PATTERN})
	)
	public void receiveEventFromBroker(byte[] event) throws Exception {

        DomainEventMessage mensagem = mapper.readValue(event, DomainEventMessage.class);
        //Fazer algo com a mensagem...
		//...

		logger.info("[PDPJ-CLOUD-EVENT] " + new String(mensagem.toString()));
	}

}
