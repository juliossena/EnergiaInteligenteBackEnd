package com.julio.energiaInteligente.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("programacaoMudancaDistancia")
public class ProgramacaoMudancaHorario extends ProgramacaoMudanca {
	private static final long serialVersionUID = 1L;

	private Integer raio;

	public Integer getRaio() {
		return raio;
	}

	public void setRaio(Integer raio) {
		this.raio = raio;
	}

}
