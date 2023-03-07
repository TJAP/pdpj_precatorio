package br.jus.pdpj.precatorio.models.dtos;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class ExemploDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "O id do exemplo.")
	private Long id;
	
	@ApiModelProperty(value = "O nome do exemplo.")
	private String nome;
	
	@Override
	public String toString() {
		return "ExemploDTO [id="+id+", nome="+nome+"]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
