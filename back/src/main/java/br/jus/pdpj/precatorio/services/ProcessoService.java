package br.jus.pdpj.precatorio.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.jus.pdpj.precatorio.amqp.MensageriaService;
import br.jus.pdpj.precatorio.models.entities.Processo;
import br.jus.pdpj.precatorio.repositories.ProcessoRepository;

import br.jus.pdpj.precatorio.models.enums.EventoMensageria;

@Service
@Validated
public class ProcessoService extends BaseService<Processo> {

    private final ProcessoRepository repository;
    private final MensageriaService mensageriaService;

    @Autowired
    public ProcessoService(ProcessoRepository repository, MensageriaService mensageriaService){
        this.repository = repository;
        this.mensageriaService = mensageriaService;
    }


    @Override
    protected JpaRepository<Processo, Long> getRepository() {
        return repository;
    }

    public List<Processo> listarProcessos(){
        return repository.findAll();
    }

    public Processo salvarProcesso(Processo p){
        return repository.save(p);
    }
    
}
