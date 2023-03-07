package br.jus.pdpj.precatorio.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.jus.pdpj.starter.base.ApiVersions;
import br.jus.pdpj.commons.annotations.PdpjApiResponses;
import br.jus.pdpj.precatorio.models.dtos.ProcessoDTO;
import br.jus.pdpj.precatorio.models.entities.PessoaPrecatorio;
import br.jus.pdpj.precatorio.models.entities.Processo;
import br.jus.pdpj.precatorio.models.enums.TipoDocumentoEnum;
import br.jus.pdpj.precatorio.models.enums.TipoPessoaPrecatorioEnum;
import br.jus.pdpj.precatorio.repositories.ProcessoRepository;
import br.jus.pdpj.precatorio.services.ProcessoService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(ApiVersions.V1 + "/processo")
@PdpjApiResponses
@AllArgsConstructor
public class ProcessoPrecatorioRestController {

    private final ProcessoRepository processoRepository;

    @ApiOperation(value = "Retorna uma lista de processos precatórios", hidden = true)
    @GetMapping(path = "/lista")
    List<Processo> getListaProcessos() {

        return processoRepository.findAll();
    }

    @ApiOperation(value = "Retorna uma lista de processos precatórios", hidden = true)
    @GetMapping(path = "/{id}")
    ResponseEntity<Processo> getProcessoById(@PathVariable Long id) {

        populaProcesso();
        Processo result = processoRepository.findById(id).get();
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna uma lista de processos precatórios", hidden = true)
    @PutMapping(path = "/save")
    ResponseEntity<Processo> salvarProcesso(@RequestBody Processo p) {

        Processo result = processoRepository.save(p);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id) {
        processoRepository.deleteById(id);
    }

    private Processo populaProcesso() {

        //processoRepository.deleteAll();

        Processo p = new Processo();
        PessoaPrecatorio pp = new PessoaPrecatorio();
        PessoaPrecatorio ppA = new PessoaPrecatorio();
        p.setNumeroProcesso("00014125444144");
        p.setOrgaoJulgador(1L);
        p.setValorCausa(new BigDecimal("15222"));
        p.setMagistrado(12L);
        p.setDataAdicao(LocalDateTime.now());

        pp.setNome("Fulano de tal");
        pp.setTipoDocumentoEnum(TipoDocumentoEnum.CPF);
        pp.setNumeroDocumento("66181275215");
        pp.setTipoPessoaPrecatorio(TipoPessoaPrecatorioEnum.CREDOR);
        pp.setProcesso(p);

        ppA.setNome("Adsv Fulano de tal");
        ppA.setTipoDocumentoEnum(TipoDocumentoEnum.OAB);
        ppA.setNumeroDocumento("456451654");
        ppA.setTipoPessoaPrecatorio(TipoPessoaPrecatorioEnum.ADV_CEDOR);
        ppA.setProcesso(p);

        List<PessoaPrecatorio> listaPessoas = new ArrayList<>();

        listaPessoas.add(pp);
        listaPessoas.add(ppA);

        // p.setPessoas(listaPessoas);

        return processoRepository.save(p);
    }

}
