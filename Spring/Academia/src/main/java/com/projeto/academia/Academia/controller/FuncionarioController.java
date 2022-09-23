package com.projeto.academia.Academia.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.academia.Academia.AcademiaApplication;
import com.projeto.academia.Academia.model.Funcionario;
import com.projeto.academia.Academia.model.ItemNivel3;
import com.projeto.academia.Academia.repository.IFuncionarioRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private IFuncionarioRepository funcionarioRepository;
	
	private final String PATH = "http://localhost:8080/funcionarios";
	
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> listAll() {
		
		List<Funcionario> response = funcionarioRepository.findAll();
		response.forEach(funcionario ->{
			setMaturidadeNivel3(funcionario);
		});
		
		return response;
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Funcionario> get(@PathVariable("id") int param) {

		List<Funcionario> response = funcionarioRepository.findById(param).stream().toList();
		return response;
		
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Funcionario create(@RequestBody Funcionario funcionarioNovo) {

		return funcionarioRepository.save(funcionarioNovo);
		
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Funcionario> update(@PathVariable("id") int param, @RequestBody Funcionario funcionarioEditado) {

			Funcionario atual = funcionarioRepository.findById(param).get();
			atual.setCpf(funcionarioEditado.getCpf());
			atual.setNome(funcionarioEditado.getNome());
			atual.setIdade(funcionarioEditado.getIdade());
			atual.setEndereco(funcionarioEditado.getEndereco());
			atual.setTelefone(funcionarioEditado.getTelefone());
			atual.setSalario(funcionarioEditado.getSalario());
			atual.setFuncao(funcionarioEditado.getFuncao());
			atual.setTurno(funcionarioEditado.getTurno());
			funcionarioRepository.save(atual);
			return funcionarioRepository.findById(param);
			
		}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody boolean deletar(@PathVariable("id") int param) {
		
		funcionarioRepository.deleteById(param);
		return funcionarioRepository.existsById(param);
		
	}
	
	private void setMaturidadeNivel3(Funcionario funcionario) {

		ArrayList<String> headers = new ArrayList();

		headers.add("Accept : application/json");
		headers.add("Content-type : application/json");

		ObjectMapper mapper = new ObjectMapper();

		mapper.setSerializationInclusion(Include.NON_NULL);

		try {

			funcionario.setLinks(null);

			String json = mapper.writeValueAsString(funcionario);

			funcionario.setLinks(new ArrayList<>());
			funcionario.getLinks().add(new ItemNivel3("GET", PATH, null, null));
			funcionario.getLinks().add(new ItemNivel3("GET", PATH + "/" + funcionario.getNome(), null, null));
			funcionario.getLinks().add(new ItemNivel3("POST", PATH, headers, json));
			funcionario.getLinks().add(new ItemNivel3("PUT", PATH + "/" + funcionario.getNome(), headers, json));

		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
	}
	
	private List<Funcionario> obterListaCompleta() {

		List<Funcionario> response = AcademiaApplication.funcionarios.stream().toList();
		response.forEach(funcionarios -> {
			setMaturidadeNivel3(funcionarios);
		});
		return response;
	}

}
