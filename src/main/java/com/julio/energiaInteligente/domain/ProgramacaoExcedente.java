package com.julio.energiaInteligente.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.julio.energiaInteligente.domain.enums.TipoExcedente;

@Entity
@JsonTypeName("programacaoExcedente")
public class ProgramacaoExcedente extends Programacao {
	private static final long serialVersionUID = 1L;

	private Integer tipoExcedente;
	private Float potencia;

	public TipoExcedente getTipoExcedente() {
		return TipoExcedente.toEnum(tipoExcedente);
	}

	public void setTipoExcedente(TipoExcedente tipoExcedente) {
		this.tipoExcedente = tipoExcedente.getCod();
	}

	public Float getPotencia() {
		return potencia;
	}

	public void setPotencia(Float potencia) {
		this.potencia = potencia;
	}

}
