package com.julio.energiaInteligente.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.julio.energiaInteligente.domain.enums.TipoEstado;

@Entity
@JsonTypeName("programacaoMudanca")
public class ProgramacaoMudanca extends Programacao {
	private static final long serialVersionUID = 1L;

	private boolean repetir;
	private Integer tipoEstado;
	private Date horario;
	private Integer raio;

	@OneToMany(mappedBy = "programacaoMudanca")
	private List<ProgramacaoMudancaRepetir> repeticoes = new ArrayList<>();

	public boolean getRepetir() {
		return repetir;
	}

	public void setRepetir(boolean repetir) {
		this.repetir = repetir;
	}

	public TipoEstado getTipoEstado() {
		return TipoEstado.toEnum(tipoEstado);
	}

	public void setTipoEstado(TipoEstado tipoEstado) {
		this.tipoEstado = tipoEstado.getCod();
	}

	public List<ProgramacaoMudancaRepetir> getRepeticoes() {
		return repeticoes;
	}

	public void setRepeticoes(List<ProgramacaoMudancaRepetir> repeticoes) {
		this.repeticoes = repeticoes;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public Integer getRaio() {
		return raio;
	}

	public void setRaio(Integer raio) {
		this.raio = raio;
	}

}
