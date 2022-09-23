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
import com.projeto.academia.Academia.model.Equipamento;
import com.projeto.academia.Academia.model.ItemNivel3;
import com.projeto.academia.Academia.repository.IEquipamentoRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/equipamentos")
public class EquipamentoController {

	@Autowired
	private IEquipamentoRepository equipamentoRepository;
	
	private final String PATH = "http://localhost:8080/equipamentos";
	
		@GetMapping()
		@ResponseStatus(HttpStatus.OK)
		public List<Equipamento> listAll() {
			
			List<Equipamento> response = equipamentoRepository.findAll();
			response.forEach(equipamento ->{
				setMaturidadeNivel3(equipamento);
			});
			return response;
		}

		@GetMapping("/{id}")
		@ResponseStatus(HttpStatus.OK)
		public @ResponseBody List<Equipamento> get(@PathVariable("id") int param) {

			List<Equipamento> response = equipamentoRepository.findById(param).stream().toList();
			return response;
		}
		
		@PostMapping()
		@ResponseStatus(HttpStatus.CREATED)
		public @ResponseBody Equipamento create(@RequestBody Equipamento equipamentoNovo) {

			return equipamentoRepository.save(equipamentoNovo);
		}

		@PutMapping("/{id}")
		@ResponseStatus(HttpStatus.OK)
		public Optional<Equipamento> update(@PathVariable("id") int param, @RequestBody Equipamento equipamentoEditado) {

				Equipamento atual = equipamentoRepository.findById(param).get();
				atual.setNome(equipamentoEditado.getNome());
				atual.setQuantidade(equipamentoEditado.getQuantidade());
				equipamentoRepository.save(atual);
				return equipamentoRepository.findById(param);
				
			}

		@DeleteMapping("/{id}")
		@ResponseStatus(HttpStatus.OK)
		public @ResponseBody boolean deletar(@PathVariable("id") int param) {
			
			equipamentoRepository.deleteById(param);
			return equipamentoRepository.existsById(param);
			
		}
		
		private void setMaturidadeNivel3(Equipamento equipamento) {

			ArrayList<String> headers = new ArrayList();

			headers.add("Accept : application/json");
			headers.add("Content-type : application/json");

			ObjectMapper mapper = new ObjectMapper();

			mapper.setSerializationInclusion(Include.NON_NULL);

			try {

				equipamento.setLinks(null);

				String json = mapper.writeValueAsString(equipamento);

				equipamento.setLinks(new ArrayList<>());
				equipamento.getLinks().add(new ItemNivel3("GET", PATH, null, null));
				equipamento.getLinks().add(new ItemNivel3("GET", PATH + "/" + equipamento.getNome(), null, null));
				equipamento.getLinks().add(new ItemNivel3("POST", PATH, headers, json));
				equipamento.getLinks().add(new ItemNivel3("PUT", PATH + "/" + equipamento.getNome(), headers, json));

			} catch (JsonProcessingException e) {

				e.printStackTrace();
			}
		}
		
		private List<Equipamento> obterListaCompleta() {

			List<Equipamento> response = AcademiaApplication.equipamentos.stream().toList();
			response.forEach(equipamento -> {
				setMaturidadeNivel3(equipamento);
			});
			return response;
		}
	
}
