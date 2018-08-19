package com.julio.energiaInteligente.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.julio.energiaInteligente.domain.enums.TipoEstado;

@Entity
@JsonTypeName("programacaoMudanca")
public class ProgramacaoMudanca extends Programacao {
	private static final long serialVersionUID = 1L;

	private Boolean repetir;
	private Integer tipoEstado;
	
	@OneToMany(mappedBy = "programacaoMudanca")
	private List<ProgramacaoMudancaRepetir> repeticoes = new ArrayList<>();

	public Boolean getRepetir() {
		return repetir;
	}

	public void setRepetir(Boolean repetir) {
		this.repetir = repetir;
	}

	public TipoEstado getTipoExcedente() {
		return TipoEstado.toEnum(tipoEstado);
	}

	public void setTipoExcedente(TipoEstado tipoEstado) {
		this.tipoEstado = tipoEstado.getCod();
	}

	public List<ProgramacaoMudancaRepetir> getRepeticoes() {
		return repeticoes;
	}

	public void setRepeticoes(List<ProgramacaoMudancaRepetir> repeticoes) {
		this.repeticoes = repeticoes;
	}

}
