package br.jus.pdpj.precatorio.auditoria;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.jus.pdpj.precatorio.models.mappers.LogEndpointMapper;
import br.jus.pdpj.precatorio.services.LogEndpointService;

@Component
public class ContentTraceEventHandler {

	private static final Logger logger = LogManager.getLogger(ContentTraceEventHandler.class);
	
    private final ApplicationEventPublisher publisher;
    private final LogEndpointService logService;
	private final LogEndpointMapper mapper;
	
	@Autowired
	public ContentTraceEventHandler(ApplicationEventPublisher publisher, LogEndpointService logService, LogEndpointMapper mapper) {
		this.publisher = publisher;
		this.logService = logService;
		this.mapper = mapper;
	}
	
    @Async
    @EventListener
    public void contentTraceEventListener(ContentTrace contentTrace) {
    	logger.info("Salvando log da requisição");
    	logService.salvar(mapper.toEntity(contentTrace));
    }

    public void publishContentTrace(ContentTrace contentTrace) {
        publisher.publishEvent(contentTrace);
    }

}