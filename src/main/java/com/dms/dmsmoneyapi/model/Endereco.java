package com.dms.dmsmoneyapi.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.dms.useful.UFBrasil;

@Embeddable
public class Endereco implements Serializable {

	private static final long serialVersionUID = 8709672423739234984L;
	private String logradouro;
	private int numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String cidade;
	@Enumerated(EnumType.STRING)
	private UFBrasil estado;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public UFBrasil getEstado() {
		return estado;
	}

	public void setEstado(UFBrasil estado) {
		this.estado = estado;
	}

}
