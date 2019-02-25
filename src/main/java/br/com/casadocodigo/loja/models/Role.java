package br.com.casadocodigo.loja.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role implements GrantedAuthority,Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String nome;

	public Role() {
	}

	public Role(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getAuthority() {
		return this.nome;
	}

	@Override
	public String toString() {
		return this.nome;
	}
}
