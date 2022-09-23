package com.projeto.academia.Academia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="clientes")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cliente extends MaturidadeNivel3 {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String cpf;
	private String nome;
	private Integer idade;
	private String telefone;
	private String endereco;
	private String tipo_plano;
	private String numero_cartao;
	
	public Cliente(Integer id,String cpf, String nome, Integer idade, String telefone, String endereco, String tipo_plano,
			String numero_cartao) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
		this.telefone = telefone;
		this.endereco = endereco;
		this.tipo_plano = tipo_plano;
		this.numero_cartao = numero_cartao;
	}
	
	public Cliente() {
		super();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTipo_plano() {
		return tipo_plano;
	}

	public void setTipo_plano(String tipo_plano) {
		this.tipo_plano = tipo_plano;
	}

	public String getNumero_cartao() {
		return numero_cartao;
	}

	public void setNumero_cartao(String numero_cartao) {
		this.numero_cartao = numero_cartao;
	}
	
	
	
	
}
