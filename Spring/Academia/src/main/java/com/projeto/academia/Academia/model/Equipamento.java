package com.projeto.academia.Academia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="equipamentos")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Equipamento extends MaturidadeNivel3{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Integer quantidade;
	
	public Equipamento(Integer id, String nome, Integer quantidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
	}
	
	public Equipamento() {
		super();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
}
