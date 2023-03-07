package br.jus.pdpj.precatorio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.pdpj.precatorio.models.entities.Exemplo;

public interface ExemploRepository extends JpaRepository<Exemplo, Long> {

	Exemplo findByNome(String username);
	
}
