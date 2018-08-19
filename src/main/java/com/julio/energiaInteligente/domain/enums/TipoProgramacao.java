package com.julio.energiaInteligente.domain.enums;

public enum TipoProgramacao {

	MAIOR(1, "Vai haver um alerta quando a potencia for maior que o especificado"), 
	MENOR(2, "Vai haver um alerta quando a potencia for menor que o especificado");

	private int cod;
	private String descricao;

	private TipoProgramacao(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoProgramacao toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (TipoProgramacao x : TipoProgramacao.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
