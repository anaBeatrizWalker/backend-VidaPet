package br.fatec.vidapet.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.fatec.vidapet.model.Agendamento;
import br.fatec.vidapet.service.AgendamentoService;

@RestController
@RequestMapping("/agenda")
public class AgendamentoController implements ControllerInterface<Agendamento>{
	
	@Autowired
	private AgendamentoService service;
	
	@Override
	@GetMapping
	public ResponseEntity<List<Agendamento>> getAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") Long id){
		Agendamento obj = service.findById(id);
		if(obj != null) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@Override
	@PostMapping
	public ResponseEntity<Agendamento> post(@RequestBody Agendamento obj){
		service.create(obj);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(location).body(obj);
	}
	
	@Override
	@PutMapping
	public ResponseEntity<?> put(@RequestBody Agendamento obj){
		if(service.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		if(service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping(value = "/funcionario/{email}")
	public ResponseEntity<List<Agendamento>> listarPorFuncionario(@PathVariable("email") String email){
		return ResponseEntity.ok(service.listarPorFuncionario(email));
	}
	
	@GetMapping(value = "/dia_atual")
	public ResponseEntity<List<Agendamento>> listarPeloDiaAtual(){
		return ResponseEntity.ok(service.listarPeloDiaAtual());
	}
	
	@GetMapping(value = "/semana_atual")
	public ResponseEntity<List<Agendamento>> listarPelaSemanaAtual(){
		return ResponseEntity.ok(service.listarPelaSemanaAtual());
	}
	
	@GetMapping(value = "/mes_atual")
	public ResponseEntity<List<Agendamento>> listarPeloMesAtual(){
		return ResponseEntity.ok(service.listarPeloMesAtual());
	}
}
