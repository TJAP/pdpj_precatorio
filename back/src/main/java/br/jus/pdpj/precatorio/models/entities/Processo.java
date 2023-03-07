package br.jus.pdpj.precatorio.models.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = Processo.TABLE_NAME, schema="precatorio")
@Data
public class Processo implements Serializable {

    public static final String TABLE_NAME = "processo";
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_" + TABLE_NAME)
	@SequenceGenerator(name = "gen_" + TABLE_NAME, sequenceName = "sq_" + TABLE_NAME, allocationSize = 1)
    private Long id;

    @Column(name = "nr_processo")
	private String numeroProcesso;

    @Column(name = "dt_adicao")
    private LocalDateTime dataAdicao;

    @Column(name = "id_orgao_julgador")
    private Long orgaoJulgador;
    
    @Column(name = "id_magistrado")
    private Long magistrado;

    @Column(name = "vl_causa")
    private BigDecimal valorCausa;

    @OneToMany(mappedBy="processo", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<PessoaPrecatorio> pessoas = new ArrayList<>();

    
}
