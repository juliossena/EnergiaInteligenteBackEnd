package com.julio.energiaInteligente.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.julio.energiaInteligente.domain.enums.DiaSemana;

@Entity
public class ProgramacaoMudancaRepetir implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer diaRepetir;

	@ManyToOne
	@JoinColumn(name = "programacao_mudanca_id")
	private ProgramacaoMudanca programacaoMudanca;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public DiaSemana getDiaRepetir() {
		return DiaSemana.toEnum(diaRepetir);
	}

	public void setDiaRepetir(DiaSemana diaRepetir) {
		this.diaRepetir = diaRepetir.getCod();
	}

	public ProgramacaoMudanca getProgramacaoMudanca() {
		return programacaoMudanca;
	}

	public void setProgramacaoMudanca(ProgramacaoMudanca programacaoMudanca) {
		this.programacaoMudanca = programacaoMudanca;
	}

}
