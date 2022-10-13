package com.projeto.academia.Academia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.academia.Academia.Responsavel;
import com.projeto.academia.Academia.model.Equipamento;

@Responsavel(quemFez = Responsavel.QuemFez.GEOVANI)
public interface IEquipamentoRepository extends JpaRepository<Equipamento, Integer> {

	public List<Equipamento> findByNome(String nome);
	
	public List<Equipamento> findByNomeStartingWith(String prefixo);
	
	public List<Equipamento> findByQuantidade(Integer valor);
	
	public List<Equipamento> findByQuantidadeBetween(Integer valor1, Integer valor2);
	
	public List<Equipamento> findByQuantidadeGreaterThan(Integer valor);
	
	public List<Equipamento> findByQuantidadeLessThan(Integer valor);
	
}
