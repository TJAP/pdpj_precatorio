package br.jus.pdpj.precatorio.models.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoPessoaPrecatorioEnum {
    
    DEVEDOR("devedor"),
    CREDOR("credor"),
    ADV_CEDOR("adv_credor");

    private String nome;


}
