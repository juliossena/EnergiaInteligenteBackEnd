package com.julio.energiaInteligente.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("programacaoMudancaHorario")
public class ProgramacaoMudancaDistancia extends ProgramacaoMudanca {
	private static final long serialVersionUID = 1L;

	private Date horario;

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

}
