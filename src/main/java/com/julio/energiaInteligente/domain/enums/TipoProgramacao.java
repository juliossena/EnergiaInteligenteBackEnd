package com.julio.energiaInteligente.domain.enums;

public enum TipoProgramacao {

	ALERTA_EXCEDENTE(1, "Alerta quando excede ou fica inferior à uma potencia determinada"), 
	ALERTA_ESTADO(2, "Alerta quando há uma mudança de estado"), 
	MUDANCA(3, "Programacao de mudanca de estado");

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
