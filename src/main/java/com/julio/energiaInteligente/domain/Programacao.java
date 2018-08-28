package com.julio.energiaInteligente.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.julio.energiaInteligente.domain.enums.TipoProgramacao;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public class Programacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer tipoProgramacao;
	private String nome;
	private boolean ligado;
	private Date ultimaRequisicao;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "circuito_id")
	private Circuito circuito;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoProgramacao getTipoProgramacao() {
		return TipoProgramacao.toEnum(tipoProgramacao);
	}

	public void setTipoProgramacao(TipoProgramacao tipoProgramacao) {
		this.tipoProgramacao = tipoProgramacao.getCod();
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

	public Circuito getCircuito() {
		return circuito;
	}

	public void setCircuito(Circuito circuito) {
		this.circuito = circuito;
	}

	public Date getUltimaRequisicao() {
		return ultimaRequisicao;
	}

	public void setUltimaRequisicao(Date ultimaRequisicao) {
		this.ultimaRequisicao = ultimaRequisicao;
	}

}
