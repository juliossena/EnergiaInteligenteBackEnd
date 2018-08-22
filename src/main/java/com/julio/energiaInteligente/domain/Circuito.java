package com.julio.energiaInteligente.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Circuito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	private String token;
	private String nome;
	private boolean ligado;

	@ManyToOne
	@JoinColumn(name = "configuracao_id")
	private ConfiguracaoCircuito configuracaoCircuito;

	@OneToMany(mappedBy = "circuito")
	private List<Medicao> medicoes = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "circuito")
	private List<Programacao> programacoes = new ArrayList<>();

	public Circuito() {

	}

	public Circuito(Integer id, Usuario usuario, String token, String nome, boolean ligado,
			ConfiguracaoCircuito configuracaoCircuito) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.token = token;
		this.nome = nome;
		this.ligado = ligado;
		this.configuracaoCircuito = configuracaoCircuito;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public ConfiguracaoCircuito getConfiguracaoCircuito() {
		return configuracaoCircuito;
	}

	public void setConfiguracaoCircuito(ConfiguracaoCircuito configuracaoCircuito) {
		this.configuracaoCircuito = configuracaoCircuito;
	}

	public List<Medicao> getMedicoes() {
		return medicoes;
	}

	public void setMedicoes(List<Medicao> medicoes) {
		this.medicoes = medicoes;
	}

	public List<Programacao> getProgramacoes() {
		return programacoes;
	}

	public void setProgramacoes(List<Programacao> programacoes) {
		this.programacoes = programacoes;
	}

}
