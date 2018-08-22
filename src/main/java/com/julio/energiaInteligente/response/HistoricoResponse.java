package com.julio.energiaInteligente.response;

import java.util.Date;

public class HistoricoResponse {

	private float consumoTotal;
	private Integer tempoLigado;
	private Integer tempoDesligado;
	private float mediaConsumo;
	private Date horarioPico;
	private float consumoPico;
	private float consumoReais;

	public HistoricoResponse() {

	}

	public HistoricoResponse(float consumoTotal, Integer tempoLigado, Integer tempoDesligado, float mediaConsumo,
			Date horarioPico, float consumoPico, float consumoReais) {
		super();
		this.consumoTotal = consumoTotal;
		this.tempoLigado = tempoLigado;
		this.tempoDesligado = tempoDesligado;
		this.mediaConsumo = mediaConsumo;
		this.horarioPico = horarioPico;
		this.consumoPico = consumoPico;
		this.consumoReais = consumoReais;
	}

	public float getConsumoTotal() {
		return consumoTotal;
	}

	public void setConsumoTotal(float consumoTotal) {
		this.consumoTotal = consumoTotal;
	}

	public Integer getTempoLigado() {
		return tempoLigado;
	}

	public void setTempoLigado(Integer tempoLigado) {
		this.tempoLigado = tempoLigado;
	}

	public Integer getTempoDesligado() {
		return tempoDesligado;
	}

	public void setTempoDesligado(Integer tempoDesligado) {
		this.tempoDesligado = tempoDesligado;
	}

	public float getMediaConsumo() {
		return mediaConsumo;
	}

	public void setMediaConsumo(float mediaConsumo) {
		this.mediaConsumo = mediaConsumo;
	}

	public Date getHorarioPico() {
		return horarioPico;
	}

	public void setHorarioPico(Date horarioPico) {
		this.horarioPico = horarioPico;
	}

	public float getConsumoPico() {
		return consumoPico;
	}

	public void setConsumoPico(float consumoPico) {
		this.consumoPico = consumoPico;
	}

	public float getConsumoReais() {
		return consumoReais;
	}

	public void setConsumoReais(float consumoReais) {
		this.consumoReais = consumoReais;
	}

}
