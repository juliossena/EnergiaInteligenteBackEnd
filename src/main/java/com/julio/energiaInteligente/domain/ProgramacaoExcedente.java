package com.julio.energiaInteligente.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.julio.energiaInteligente.domain.enums.TipoExcedente;

@Entity
@JsonTypeName("programacaoExcedente")
public class ProgramacaoExcedente extends Programacao {
	private static final long serialVersionUID = 1L;

	private Integer tipoExcedente;
	private Float potencia;
	private Date ultimoAlerta;

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

	public Date getUltimoAlerta() {
		return ultimoAlerta;
	}

	public void setUltimoAlerta(Date ultimoAlerta) {
		this.ultimoAlerta = ultimoAlerta;
	}

}
