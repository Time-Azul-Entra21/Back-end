package com.projeto.academia.Academia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.academia.Academia.Responsavel;
import com.projeto.academia.Academia.model.Cliente;
import com.projeto.academia.Academia.model.Funcionario;

@Responsavel(quemFez = Responsavel.QuemFez.GEOVANI)
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

	public List<Cliente> findByNome(String nome);
	
	public List<Cliente> findByNomeStartingWith(String prefixo);
	
	public List<Cliente> findByIdade(Integer idade);
	
	public List<Cliente> findByIdadeGreaterThan(Integer idade);
	
	public List<Cliente> findByIdadeLessThan(Integer idade);
	
	public List<Cliente> findByIdadeBetween(Integer valor1, Integer valor2);
	
	public List<Cliente> findByNomeStartingWithAndIdadeGreaterThan(String prefixo, Integer idade);
	
	public List<Cliente> findByNomeStartingWithAndIdadeLessThan(String prefixo, Integer idade);
	
	public List<Cliente> findByNomeStartingWithAndIdadeBetween(String prefixo, Integer valor1, Integer valor2);
	
	public List<Cliente> findByNomeStartingWithAndTipoplano(String prefixo, String tipoplano);
	
	public List<Cliente> findByCpf(String cpf);	
	
	public List<Cliente> findByTelefone(String telefone);
	
	public List<Cliente> findByEndereco(String endereco);

	public List<Cliente> findByTipoplano(String tipoplano);
	
	public List<Cliente> findByNumerocartao(String numerocartao);
	
}
