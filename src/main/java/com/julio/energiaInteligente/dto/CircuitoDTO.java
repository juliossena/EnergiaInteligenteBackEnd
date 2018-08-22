package com.julio.energiaInteligente.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.julio.energiaInteligente.domain.Circuito;

public class CircuitoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 2, max = 120, message = "O tamanho deve ser entre 2 e 120 caracteres")
	private String nome;

	private boolean ligado;

	public CircuitoDTO() {

	}

	public CircuitoDTO(Circuito obj) {
		super();
		this.nome = obj.getNome();
		this.ligado = obj.getLigado();
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

	public boolean getLigado() {
		return ligado;
	}

	public void setLigado(boolean ligado) {
		this.ligado = ligado;
	}

	@Override
	public String toString() {
		return "CircuitoDTO [id=" + id + ", nome=" + nome + ", ligado=" + ligado + "]";
	}

}
