package br.jus.pdpj.precatorio.models.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class InclusaoAlteracaoExemploDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@ApiModelProperty(value = "O nome do exemplo.", example="Nome de teste", required = true)
	private String nome;
	
	@Override
	public String toString() {
		return "InclusaoAlteracaoExemploDTO [nome=" + nome + "]";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
