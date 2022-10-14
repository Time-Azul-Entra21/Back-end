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
import com.projeto.academia.Academia.Responsavel;
import com.projeto.academia.Academia.Responsavel.QuemFez;
import com.projeto.academia.Academia.model.Cliente;
import com.projeto.academia.Academia.model.Funcionario;
import com.projeto.academia.Academia.model.ItemNivel3;
import com.projeto.academia.Academia.repository.IFuncionarioRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/funcionarios")
@Responsavel(quemFez = Responsavel.QuemFez.GEOVANI)
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
	
	@GetMapping("/buscarnome/{nome}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getByNome(@PathVariable("nome") String nome){
		
		return funcionarioRepository.findByNome(nome);
	}
	
	@GetMapping("/nomecomecando/{prefixo}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getByStartWith(@PathVariable("prefixo") String letra){
		
		return funcionarioRepository.findByNomeStartingWith(letra);
	}
	
	@GetMapping("/buscaridade/{idade}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getByIdade(@PathVariable("idade") Integer idade){
		
		return funcionarioRepository.findByIdade(idade);
	}
	
	@GetMapping("/idademaiorque/{idade}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getByIdadeGreaterThan(@PathVariable("idade") Integer idade){
		
		return funcionarioRepository.findByIdadeGreaterThan(idade);
	}
	
	@GetMapping("/idademenorque/{idade}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getByIdadeLessThan(@PathVariable("idade") Integer idade){
		
		return funcionarioRepository.findByIdadeLessThan(idade);
	}
	
	@GetMapping("/idadeentre/{valor1}/{valor2}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getByIdadeBetween(@PathVariable("valor1") Integer valor1, @PathVariable("valor2") Integer valor2){
		
		return funcionarioRepository.findByIdadeBetween(valor1, valor2);
	}
	
	@GetMapping("/nomecomecandoidademaior/{prefixo}/{idade}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getByStartWithAndIdadeGreaterThan(@PathVariable("prefixo") String letra, @PathVariable("idade") Integer idade){
		
		return funcionarioRepository.findByNomeStartingWithAndIdadeGreaterThan(letra, idade);
	}
	
	@GetMapping("/nomecomecandoidademenor/{prefixo}/{idade}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getByStartWithAndIdadeLessThan(@PathVariable("prefixo") String letra, @PathVariable("idade") Integer idade){
		
		return funcionarioRepository.findByNomeStartingWithAndIdadeLessThan(letra, idade);
	}
	
	@GetMapping("/nomecomecandoidadeentre/{prefixo}/{valor1}/{valor2}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getByStartWithAndIdadeBetween(@PathVariable("prefixo") String letra, @PathVariable("valor1") Integer valor1, @PathVariable ("valor2") Integer valor2){
		
		return funcionarioRepository.findByNomeStartingWithAndIdadeBetween(letra, valor1, valor2);
	}
	
	@GetMapping("/buscarfuncao/{funcao}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getByFuncao(@PathVariable("funcao") String funcao){
		
		return funcionarioRepository.findByFuncao(funcao);
	}
	
	@GetMapping("/buscarturno/{turno}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getByTurno(@PathVariable("turno") String turno){
		
		return funcionarioRepository.findByTurno(turno);
	}
	
	@GetMapping("/buscarcpf/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getByCpf(@PathVariable("cpf") String cpf){
		
		return funcionarioRepository.findByCpf(cpf);
	}
	
	@GetMapping("/buscartel/{telefone}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getByTelefone(@PathVariable("telefone") String telefone){
		
		return funcionarioRepository.findByTelefone(telefone);
	}
	
	@GetMapping("/buscarendereco/{endereco}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getByEndereco(@PathVariable("endereco") String endereco){
		
		return funcionarioRepository.findByEndereco(endereco);
	}
	
	@GetMapping("/buscarsalario/{salario}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getBySalario(@PathVariable("salario") Integer salario){
		
		return funcionarioRepository.findBySalario(salario);
	}
	
	@GetMapping("/salariomaiorque/{salario}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getBySalarioGreaterThan(@PathVariable("salario") Integer salario){
		
		return funcionarioRepository.findBySalarioGreaterThan(salario);
	}
	
	@GetMapping("/salariomenorque/{salario}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getBySalarioLessThan(@PathVariable("salario") Integer salario){
		
		return funcionarioRepository.findBySalarioLessThan(salario);
	}
	
	@GetMapping("/salarioentre/{valor1}/{valor2}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getBySalarioBetween(@PathVariable("valor1") Integer valor1, @PathVariable("valor2") Integer valor2){
		
		return  funcionarioRepository.findBySalarioBetween(valor1, valor2);
	}
	
	@GetMapping("/buscarusuario/{username}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getByUsername(@PathVariable("username") String username){
		
		return funcionarioRepository.findByUsername(username);
	}
	
	@GetMapping("/usuariocomecando/{prefixo}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getByUsernameStartWith(@PathVariable("prefixo") String letra){
		
		return funcionarioRepository.findByUsernameStartingWith(letra);
	}
	
	//nao sei porque alguem buscaria outro pela senha dele, mas ta ai \/('-')\/
	@GetMapping("/buscarsenha/{senha}")
	@ResponseStatus(HttpStatus.OK)
	public List<Funcionario> getByPassword(@PathVariable("senha") String senha){
		
		return funcionarioRepository.findByPassword(senha);
	}
	
	@Responsavel(quemFez = Responsavel.QuemFez.HENRIQUE)
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Funcionario> login(@RequestBody Funcionario credencial) {
		
		List<Funcionario> response = funcionarioRepository.findAll().stream()
				.filter(funcionario -> funcionario.getUsername().equals(credencial.getUsername())
						&& funcionario.getPassword().equals(credencial.getPassword()))
				.toList();
		response.forEach(funcionario -> {
			setMaturidadeNivel3(funcionario);
			
		});
		
		return response;
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
