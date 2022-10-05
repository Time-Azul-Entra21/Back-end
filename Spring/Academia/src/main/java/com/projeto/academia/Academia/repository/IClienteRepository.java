package com.projeto.academia.Academia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.academia.Academia.model.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

	public List<Cliente> findByIdadeGreaterThanEqual(Integer idade);
	
	public List<Cliente> findByNomeStartingWith(String prefixo);
	
	public List<Cliente> findByNomeStartingWithAndIdadeGreaterThanEqual(String prefixo, Integer idade);
	
	public List<Cliente> findByIdadeLessThanEqual(Integer idade);
	
	public List<Cliente> findByNomeStartingWithAndIdadeLessThanEqual(String prefixo, Integer idade);
	
	public List<Cliente> findByIdadeBetween(Integer valor1, Integer valor2);
	
}
