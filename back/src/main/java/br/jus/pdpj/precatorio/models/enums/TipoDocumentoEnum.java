package br.jus.pdpj.precatorio.models.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoDocumentoEnum {
    
    CPF("CPF"),
    RG("RG"),
    OAB("OAB"),
    CNH("CNH");

    private String nome;
}
