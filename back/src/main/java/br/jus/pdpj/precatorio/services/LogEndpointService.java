package br.jus.pdpj.precatorio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.pdpj.precatorio.models.entities.LogEndpoint;
import br.jus.pdpj.precatorio.repositories.LogEndpointRepository;

@Service
public class LogEndpointService {

	private final LogEndpointRepository repository;
	
	@Autowired
	public LogEndpointService(LogEndpointRepository repository) {
		this.repository = repository;
	}
	
	public void salvar(LogEndpoint trace) {
		repository.save(trace);
	}
}
