package com.julio.energiaInteligente.domain.enums;

public enum DiaSemana {

	SABADO(0, "Sabado"),
	DOMINGO(1, "Domingo"), 
	SEGUNDA(2, "Segunda-feira"),
	TERCA(3, "Terca-feira"),
	QUARTA(4, "Quarta-feira"), 
	QUINTA(5, "Quinta-feira"),
	SEXTA(6, "Sexta-feira");

	private int cod;
	private String descricao;

	private DiaSemana(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static DiaSemana toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (DiaSemana x : DiaSemana.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
