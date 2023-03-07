package br.jus.pdpj.precatorio.models.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProcessoDTO implements Serializable {
	
    private static final long serialVersionUID = 1L;

    private Long id;
	private String numeroProcesso;
    private LocalDateTime dataAdicao;
    private Long orgaoJulgador;
    private Long magistrado;
    private BigDecimal valorCausa;
}
