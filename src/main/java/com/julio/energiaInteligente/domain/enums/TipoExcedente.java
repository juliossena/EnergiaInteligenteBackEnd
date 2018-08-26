package com.julio.energiaInteligente.domain.enums;

public enum TipoExcedente {

	MAIOR(0, "Vai haver um alerta quando a potencia for maior que o especificado"),
	MENOR(1, "Vai haver um alerta quando a potencia for menor que o especificado");

	private int cod;
	private String descricao;

	private TipoExcedente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoExcedente toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (TipoExcedente x : TipoExcedente.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
