package com.AppRH.AppRH.models;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Vaga implements Serializable{

	private static final long serialVersionUID =1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	
	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String descricao;
	
	@NotEmpty
	private String data;
	
	@NotEmpty
	private String salario;
	
	@OneToMany(mappedBy = "vaga", cascade = CascadeType.REMOVE)
	private List<Candidato> candidato;
	
	
	public long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(long novo) {
		this.codigo = novo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String novo) {
		this.nome = novo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String novo) {
		this.descricao = novo;
	}

	public String getData() {
		return data;
	}

	public void setData(String novo) {
		this.data = novo;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String novo) {
		this.salario = novo;
	}

	public List<Candidato> getCandidato() {
		return candidato;
	}

	public void setCandidato(List<Candidato> candidato) {
		this.candidato = candidato;
	}
	
	
	
}
