package br.jus.pdpj.precatorio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.jus.pdpj.precatorio.models.entities.Processo;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
 
}
