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

import br.fatec.vidapet.dto.AnimalDTO;
import br.fatec.vidapet.mapper.AnimalMapper;
import br.fatec.vidapet.model.Animal;
import br.fatec.vidapet.service.AnimalService;

@RestController
@RequestMapping("/animais")
public class AnimalController implements ControllerInterface<AnimalDTO>{
	
	@Autowired
	private AnimalService service;
	
	@Autowired 
	private AnimalMapper mapper; 
	
	@Override
	@GetMapping
	public ResponseEntity<List<AnimalDTO>> getAll(){
		return ResponseEntity.ok(mapper.toDTO(service.findAll()));
	}
	
	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<AnimalDTO> getOne(@PathVariable("id") Long id){
		Animal obj = service.findById(id);
		if(obj != null) {
			return ResponseEntity.ok(mapper.toDTO(obj));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@Override
	@PostMapping
	public ResponseEntity<AnimalDTO> post(@Valid @RequestBody AnimalDTO obj) throws URISyntaxException{
		Animal animal = service.create(mapper.toEntity(obj));
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(animal.getId()).toUri();
		return ResponseEntity.created(location).body(mapper.toDTO(animal));
	}
	
	@Override
	@PutMapping
	public ResponseEntity<AnimalDTO> put(@Valid @RequestBody AnimalDTO obj){
		if(service.update(mapper.toEntity(obj))) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		if(service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
