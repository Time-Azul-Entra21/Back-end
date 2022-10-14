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
	private String tipoplano;
	private String numerocartao;
	private String username;
	private String password;
	
	public Cliente(Integer id, String cpf, String nome, Integer idade, String telefone, String endereco,
			String tipoplano, String numerocartao, String username, String password) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
		this.telefone = telefone;
		this.endereco = endereco;
		this.tipoplano = tipoplano;
		this.numerocartao = numerocartao;
		this.username = username;
		this.password = password;
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

	public String getTipoplano() {
		return tipoplano;
	}

	public void setTipoplano(String tipoplano) {
		this.tipoplano = tipoplano;
	}

	public String getNumerocartao() {
		return numerocartao;
	}

	public void setNumerocartao(String numerocartao) {
		this.numerocartao = numerocartao;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
