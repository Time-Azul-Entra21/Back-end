package com.projeto.academia.Academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.academia.Academia.model.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

}
