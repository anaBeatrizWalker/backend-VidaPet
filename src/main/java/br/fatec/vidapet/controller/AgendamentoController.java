package br.fatec.vidapet.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

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

import br.fatec.vidapet.dto.AgendamentoDTO;
import br.fatec.vidapet.mapper.AgendamentoMapper;
import br.fatec.vidapet.model.Agendamento;
import br.fatec.vidapet.service.AgendamentoService;

@RestController
@RequestMapping("/agenda")
public class AgendamentoController implements ControllerInterface<AgendamentoDTO>{
	
	@Autowired
	private AgendamentoService service;
	
	@Autowired 
	private AgendamentoMapper mapper; 
	
	@Override
	@GetMapping
	public ResponseEntity<List<AgendamentoDTO>> getAll(){
		return ResponseEntity.ok(mapper.toDTO(service.listarAgendaOrdenadaAsc()));
	}
	
	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<AgendamentoDTO> getOne(@PathVariable("id") Long id){
		Agendamento obj = service.findById(id);
		if(obj != null) {
			return ResponseEntity.ok(mapper.toDTO(obj));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@Override
	@PostMapping
	public ResponseEntity<AgendamentoDTO> post(@Valid @RequestBody AgendamentoDTO obj) throws URISyntaxException{
		Agendamento agendamento = service.create(mapper.toEntity(obj));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(agendamento.getId()).toUri();
		return ResponseEntity.created(location).body(mapper.toDTO(agendamento));
	}
	
	@Override
	@PutMapping
	public ResponseEntity<AgendamentoDTO> put(@Valid @RequestBody AgendamentoDTO obj){
		if(service.update(mapper.toEntity(obj))) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		if(service.delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping(value = "/funcionario/{email}")
	public ResponseEntity<List<AgendamentoDTO>> listarPorFuncionario(@PathVariable("email") String email){
		List<Agendamento> obj = service.listarPorFuncionario(email); 
		if (obj != null) 
			return ResponseEntity.ok(mapper.toDTO(obj));
		return ResponseEntity.notFound().build(); 
	}
	
	@GetMapping(value = "/dia_atual")
	public ResponseEntity<List<AgendamentoDTO>> listarPeloDiaAtual(){
		List<Agendamento> obj = service.listarPeloDiaAtual();
		if (obj != null) 
			return ResponseEntity.ok(mapper.toDTO(obj));
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(value = "/semana_atual")
	public ResponseEntity<List<AgendamentoDTO>> listarPelaSemanaAtual(){
		List<Agendamento> obj = service.listarPelaSemanaAtual(); 
		if (obj != null) 
			return ResponseEntity.ok(mapper.toDTO(obj));
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(value = "/mes_atual")
	public ResponseEntity<List<AgendamentoDTO>> listarPeloMesAtual(){
		List<Agendamento> obj = service.listarPeloMesAtual(); 
		if (obj != null) 
			return ResponseEntity.ok(mapper.toDTO(obj));
		return ResponseEntity.notFound().build();
	}
}
