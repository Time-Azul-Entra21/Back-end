package com.projeto.academia.Academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.academia.Academia.model.Funcionario;

public interface IFuncionarioRepository extends JpaRepository<Funcionario, Integer>{

}
