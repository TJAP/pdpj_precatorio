package br.jus.pdpj.precatorio.models.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;

import br.jus.pdpj.precatorio.models.enums.TipoDocumentoEnum;
import br.jus.pdpj.precatorio.models.enums.TipoPessoaPrecatorioEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = PessoaPrecatorio.TABLE_NAME, schema="precatorio")
@Data
public class PessoaPrecatorio implements Serializable {

    public static final String TABLE_NAME = "pessoa_precatorio";
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_" + TABLE_NAME)
	@SequenceGenerator(name = "gen_" + TABLE_NAME, sequenceName = "sq_" + TABLE_NAME, allocationSize = 1)
    private Long id;

    @ManyToOne
	private Processo processo;

    @Column(name = "tp_pessoa_precatorio")
    private TipoPessoaPrecatorioEnum tipoPessoaPrecatorio;

    @Column(name = "tp_documento")
    private TipoDocumentoEnum tipoDocumentoEnum;

    @Column(name = "nr_documento")
    private String numeroDocumento;

    @Column(name = "nome")
    private String nome;

    @Column(name = "vl_bruto")
    private BigDecimal valorBruto;
    
    @Column(name = "vl_liquido")
    private BigDecimal valorLiquido;

    @Column(name = "dt_cadastro")
    private LocalDateTime dataCadastro;
 
}
