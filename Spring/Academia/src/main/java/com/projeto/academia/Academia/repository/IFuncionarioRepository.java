package com.projeto.academia.Academia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.academia.Academia.Responsavel;
import com.projeto.academia.Academia.model.Funcionario;

@Responsavel(quemFez = Responsavel.QuemFez.GEOVANI)
public interface IFuncionarioRepository extends JpaRepository<Funcionario, Integer>{
	
	public List<Funcionario> findByNome(String nome);
	
	public List<Funcionario> findByNomeStartingWith(String prefixo);
	
	public List<Funcionario> findByIdade(Integer idade);
	
	public List<Funcionario> findByIdadeGreaterThan(Integer idade);
	
	public List<Funcionario> findByIdadeLessThan(Integer idade);
	
	public List<Funcionario> findByIdadeBetween(Integer valor1, Integer valor2);
	
	public List<Funcionario> findByNomeStartingWithAndIdadeGreaterThan(String prefixo, Integer idade);
	
	public List<Funcionario> findByNomeStartingWithAndIdadeLessThan(String prefixo, Integer idade);
	
	public List<Funcionario> findByNomeStartingWithAndIdadeBetween(String prefixo, Integer valor1, Integer valor2);
	
	public List<Funcionario> findByTurno(String turno);
	
	public List<Funcionario> findByFuncao(String funcao);
	
	public List<Funcionario> findByCpf(String cpf);	
	
	public List<Funcionario> findByTelefone(String telefone);
	
	public List<Funcionario> findByEndereco(String endereco);
	
	public List<Funcionario> findBySalario(Integer salario);
	
	public List<Funcionario> findBySalarioBetween(Integer valor1, Integer valor2);
	
	public List<Funcionario> findBySalarioGreaterThan(Integer salario);
	
	public List<Funcionario> findBySalarioLessThan(Integer salario);
		
}
