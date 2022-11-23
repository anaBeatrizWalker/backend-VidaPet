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

import br.fatec.vidapet.dto.AdministradorDTO;
import br.fatec.vidapet.mapper.AdministradorMapper;
import br.fatec.vidapet.model.Administrador;
import br.fatec.vidapet.service.AdministradorService;

@RestController
@RequestMapping("/adm")
public class AdministradorController implements ControllerInterface<AdministradorDTO>{
	
	@Autowired
	private AdministradorService service;
	
	@Autowired 
	private AdministradorMapper mapper;
	
	@Override
	@GetMapping
	public ResponseEntity<List<AdministradorDTO>> getAll(){
		return ResponseEntity.ok(mapper.toDTO(service.findAll()));
	}
	
	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<AdministradorDTO> getOne(@PathVariable("id") Long id){
		Administrador obj = service.findById(id);
		if(obj != null) {
			return ResponseEntity.ok(mapper.toDTO(obj));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@Override
	@PostMapping
	public ResponseEntity<AdministradorDTO> post(@Valid @RequestBody AdministradorDTO obj) throws URISyntaxException{
		Administrador adm = service.create(mapper.toEntity(obj));
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(adm.getId()).toUri();
		return ResponseEntity.created(location).body(mapper.toDTO(adm));
	}
	
	@Override
	@PutMapping
	public ResponseEntity<AdministradorDTO> put(@Valid @RequestBody AdministradorDTO obj){
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
}
