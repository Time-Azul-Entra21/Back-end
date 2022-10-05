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
import com.projeto.academia.Academia.model.Cliente;
import com.projeto.academia.Academia.model.ItemNivel3;
import com.projeto.academia.Academia.repository.IClienteRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/clientes")
public class ClienteController {
	
	
	@Autowired
	private IClienteRepository clienteRepository;
	
	private final String PATH = "http://localhost:8080/clientes";
	
		// Get para obter uma lista de clientes
		@GetMapping()
		@ResponseStatus(HttpStatus.OK)
		public List<Cliente> listAll() {
			
			List<Cliente> response = clienteRepository.findAll();
			response.forEach(cliente ->{
				setMaturidadeNivel3(cliente);
			});
			return response;
		}

		// Get para obter um cliente por id
		@GetMapping("/{id}")
		@ResponseStatus(HttpStatus.OK)
		public @ResponseBody List<Cliente> get(@PathVariable("id") int param) {

			List<Cliente> response = clienteRepository.findById(param).stream().toList();
			return response;
		}
		
		// Post para criar um cliente
		@PostMapping()
		@ResponseStatus(HttpStatus.CREATED)
		public @ResponseBody Cliente create(@RequestBody Cliente clienteNovo) {

			return clienteRepository.save(clienteNovo);
		}

		// Put para atualizar um cliente
		@PutMapping("/{id}")
		@ResponseStatus(HttpStatus.OK)
		public Optional<Cliente> update(@PathVariable("id") int param, @RequestBody Cliente clienteEditado) {

				Cliente atual = clienteRepository.findById(param).get(); //encontre pelo ID e me traga o resultado
				atual.setCpf(clienteEditado.getCpf());
				atual.setNome(clienteEditado.getNome());
				atual.setIdade(clienteEditado.getIdade());
				atual.setEndereco(clienteEditado.getEndereco());
				atual.setTelefone(clienteEditado.getTelefone());
				atual.setTipo_plano(clienteEditado.getTipo_plano());
				atual.setNumero_cartao(clienteEditado.getNumero_cartao());
				clienteRepository.save(atual);
				return clienteRepository.findById(param);
			}

		// Delete para remover um cliente
		@DeleteMapping("/{id}")
		@ResponseStatus(HttpStatus.OK)
		public @ResponseBody boolean deletar(@PathVariable("id") int param) {
			
			clienteRepository.deleteById(param);
			return clienteRepository.existsById(param);
			
		}
		
		@GetMapping("/idademaiorigual/{idade}")
		@ResponseStatus(HttpStatus.OK)
		public List<Cliente> getByIdadeGreaterThanEqual(@PathVariable ("idade") Integer idade ){
			
			return clienteRepository.findByIdadeGreaterThanEqual(idade);
		}
		
		@GetMapping("/nomecomecando/{prefixo}")
		@ResponseStatus(HttpStatus.OK)
		public List<Cliente> getStartWith(@PathVariable ("prefixo") String tantoFaz){
			
			return clienteRepository.findByNomeStartingWith(tantoFaz);
		}
		
		//Que código grande pqp
		@GetMapping("/nomecomecandoidademaiorigual/{prefixo}/{idade}")
		@ResponseStatus(HttpStatus.OK)
		public List<Cliente> getByStartWithAndIdadeGreaterThan(@PathVariable ("prefixo") String tantoFaz, @PathVariable("idade") Integer idade){
			
			return clienteRepository.findByNomeStartingWithAndIdadeGreaterThanEqual(tantoFaz, idade);
		}
		
		@GetMapping("/idademenorigual/{idade}")
		@ResponseStatus(HttpStatus.OK)
		public List<Cliente> getByIdadeLessThanEqual(@PathVariable ("idade") Integer idade){
			
			return clienteRepository.findByIdadeLessThanEqual(idade);
		}
		
		@GetMapping("/nomecomecandoidademenorigual/{prefixo}/{idade}")
		@ResponseStatus(HttpStatus.OK)
		public List<Cliente> getByStartWithAndIdadeLessThan(@PathVariable ("prefixo") String qualquer, @PathVariable("idade") Integer idade){
			
			return clienteRepository.findByNomeStartingWithAndIdadeLessThanEqual(qualquer, idade);
		}
		
		@GetMapping("/idadeentre/{valor1}/{valor2}")
		@ResponseStatus(HttpStatus.OK)
		public List<Cliente> getByIdadeBetween(@PathVariable ("valor1") Integer idade1, @PathVariable ("valor2") Integer idade2){
			
			return clienteRepository.findByIdadeBetween(idade1, idade2);
		}
		
		private void setMaturidadeNivel3(Cliente cliente) {

			ArrayList<String> headers = new ArrayList();

			headers.add("Accept : application/json");
			headers.add("Content-type : application/json");

			ObjectMapper mapper = new ObjectMapper();

			mapper.setSerializationInclusion(Include.NON_NULL);

			try {

				cliente.setLinks(null);

				String json = mapper.writeValueAsString(cliente);

				cliente.setLinks(new ArrayList<>());
				cliente.getLinks().add(new ItemNivel3("GET", PATH, null, null));
				cliente.getLinks().add(new ItemNivel3("GET", PATH + "/" + cliente.getNome(), null, null));
				cliente.getLinks().add(new ItemNivel3("POST", PATH, headers, json));
				cliente.getLinks().add(new ItemNivel3("PUT", PATH + "/" + cliente.getNome(), headers, json));

			} catch (JsonProcessingException e) {

				e.printStackTrace();
			}
		}
		
		private List<Cliente> obterListaCompleta() {

			List<Cliente> response = AcademiaApplication.clientes.stream().toList();
			response.forEach(cliente -> {
				setMaturidadeNivel3(cliente);
			});
			return response;
		}
}
