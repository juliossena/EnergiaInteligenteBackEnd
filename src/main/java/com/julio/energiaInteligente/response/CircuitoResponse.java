package com.julio.energiaInteligente.response;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.julio.energiaInteligente.domain.Circuito;
import com.julio.energiaInteligente.domain.Medicao;

public class CircuitoResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private boolean ligado;

	@OneToMany(mappedBy = "circuito")
	private List<Medicao> medicoes = new ArrayList<>();

	public CircuitoResponse() {

	}

	public CircuitoResponse(Circuito circuito) {
		this.id = circuito.getId();
		this.nome = circuito.getNome();
		this.ligado = circuito.getLigado();
		this.medicoes = circuito.getMedicoes();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isLigado() {
		return ligado;
	}

	public void setLigado(boolean ligado) {
		this.ligado = ligado;
	}

	public List<Medicao> getMedicoes() {
		return medicoes;
	}

	public void setMedicoes(List<Medicao> medicoes) {
		this.medicoes = medicoes;
	}

}
