package com.julio.energiaInteligente.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ConfiguracaoCircuito implements Serializable { 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String ssid;
	private String senhaSsid;
	private Integer tempoAtualizacao;
	private float custoPorW;

	public ConfiguracaoCircuito() {

	}

	public ConfiguracaoCircuito(Integer id, String ssid, String senhaSsid, Integer tempoAtualizacao, float custoPorW) {
		super();
		this.id = id;
		this.ssid = ssid;
		this.senhaSsid = senhaSsid;
		this.tempoAtualizacao = tempoAtualizacao;
		this.custoPorW = custoPorW;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getSenhaSsid() {
		return senhaSsid;
	}

	public void setSenhaSsid(String senhaSsid) {
		this.senhaSsid = senhaSsid;
	}

	public Integer getTempoAtualizacao() {
		return tempoAtualizacao;
	}

	public void setTempoAtualizacao(Integer tempoAtualizacao) {
		this.tempoAtualizacao = tempoAtualizacao;
	}

	public float getCustoPorW() {
		return custoPorW;
	}

	public void setCustoPorW(float custoPorW) {
		this.custoPorW = custoPorW;
	}

}
