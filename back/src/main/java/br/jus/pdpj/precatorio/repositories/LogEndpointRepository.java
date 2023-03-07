package br.jus.pdpj.precatorio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.pdpj.precatorio.models.entities.LogEndpoint;

public interface LogEndpointRepository extends JpaRepository<LogEndpoint, Long> {
	
}
