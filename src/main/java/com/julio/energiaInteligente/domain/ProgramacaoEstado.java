package com.julio.energiaInteligente.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.julio.energiaInteligente.domain.enums.TipoEstado;

@Entity
@JsonTypeName("programacaoEstado")
public class ProgramacaoEstado extends Programacao {
	private static final long serialVersionUID = 1L;

	private Integer tipoEstado;

	public TipoEstado getTipoEstado() {
		return TipoEstado.toEnum(tipoEstado);
	}

	public void setTipoEstado(TipoEstado tipoEstado) {
		this.tipoEstado = tipoEstado.getCod();
	}


}
