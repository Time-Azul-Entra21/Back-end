package com.projeto.academia.Academia;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.projeto.academia.Academia.model.Cliente;
import com.projeto.academia.Academia.model.Funcionario;
import com.projeto.academia.Academia.model.Equipamento;

@SpringBootApplication
public class AcademiaApplication implements CommandLineRunner {
	
	@Autowired
	private JdbcTemplate jdbc;
	public static void main(String[] args) {
		SpringApplication.run(AcademiaApplication.class, args);
	}
	
	public static ArrayList<Cliente> clientes=new ArrayList<Cliente>();
	public static ArrayList<Funcionario> funcionarios=new ArrayList<Funcionario>();
	public static ArrayList<Equipamento> equipamentos=new ArrayList<Equipamento>();

	@Override
	public void run(String... args) throws Exception {
		
		
	}

}
