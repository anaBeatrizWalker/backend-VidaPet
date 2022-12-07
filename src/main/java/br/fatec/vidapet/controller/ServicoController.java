package br.fatec.vidapet.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.fatec.vidapet.dto.ServicoDTO;
import br.fatec.vidapet.mapper.ServicoMapper;
import br.fatec.vidapet.model.Servico;
import br.fatec.vidapet.service.ServicoService;

@RestController
@RequestMapping("/servicos")
public class ServicoController implements ControllerInterface<ServicoDTO>{
	
	@Autowired
	private ServicoService service;	
	
	@Autowired 
	private ServicoMapper mapper; 
	
	@Override
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<ServicoDTO>> getAll(){
		return ResponseEntity.ok(mapper.toDTO(service.listarServicosOrdenadosAsc()));
	}
	
	@Override
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ServicoDTO> getOne(@PathVariable("id") Long id){
		Servico obj = service.findById(id);
		if(obj != null) {
			return ResponseEntity.ok(mapper.toDTO(obj));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@Override
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ServicoDTO> post(@Valid @RequestBody ServicoDTO obj) throws URISyntaxException{
		Servico servico = service.create(mapper.toEntity(obj));
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(servico.getId()).toUri();
		return ResponseEntity.created(location).body(mapper.toDTO(servico));
	}
	
	@Override
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ServicoDTO> put(@Valid @RequestBody ServicoDTO obj){
		if(service.update(mapper.toEntity(obj))) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@Override
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		if(service.delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
