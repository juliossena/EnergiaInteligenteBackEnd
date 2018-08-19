package com.julio.energiaInteligente.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Medicao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "circuito_id")
	private Circuito circuito;

	private Float tensao;
	private Float corrente;
	private Float potencia;
	private Date horario;

	public Medicao() {

	}

	public Medicao(Integer id, Circuito circuito, Float tensao, Float corrente, Float potencia, Date horario) {
		super();
		this.id = id;
		this.circuito = circuito;
		this.tensao = tensao;
		this.corrente = corrente;
		this.potencia = potencia;
		this.horario = horario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Circuito getCircuito() {
		return circuito;
	}

	public void setCircuito(Circuito circuito) {
		this.circuito = circuito;
	}

	public Float getTensao() {
		return tensao;
	}

	public void setTensao(Float tensao) {
		this.tensao = tensao;
	}

	public Float getCorrente() {
		return corrente;
	}

	public void setCorrente(Float corrente) {
		this.corrente = corrente;
	}

	public Float getPotencia() {
		return potencia;
	}

	public void setPotencia(Float potencia) {
		this.potencia = potencia;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicao other = (Medicao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
