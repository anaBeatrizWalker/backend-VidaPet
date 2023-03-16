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

import br.fatec.vidapet.dto.ClienteDTO;
import br.fatec.vidapet.exception.AuthorizationException;
import br.fatec.vidapet.mapper.ClienteMapper;
import br.fatec.vidapet.model.Cliente;
import br.fatec.vidapet.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/clientes")
public class ClienteController implements ControllerInterface<ClienteDTO>{
	
	@Autowired
	private ClienteService service;
	
	@Autowired 
	private ClienteMapper mapper;
	
	@Override
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE')")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorno da lista de clientes."),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar esse conteúdo."),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema.")
	})
	@Operation(summary = "Retorno da lista de clientes")
	public ResponseEntity<List<ClienteDTO>> getAll(){
		List<Cliente> objts = service.findAll();
		if(objts != null) {
			return ResponseEntity.ok(mapper.toDTO(objts));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@Override
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE', 'FUNCIONARIO', 'CLIENTE')")
	@Operation(summary = "Retorno de um cliente")
	public ResponseEntity<ClienteDTO> getOne(@PathVariable("id") Long id){
		/* 
		Cliente obj = service.findById(id);
		if(obj != null) {
			return ResponseEntity.ok(mapper.toDTO(obj));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		*/
		/* */
		try {
			Cliente obj = service.findById(id);
			if(obj != null) {
				return ResponseEntity.ok(mapper.toDTO(obj));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (AuthorizationException e) { 
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); 
		}
		
	}
	
	@Override
	@PostMapping
	@Operation(summary = "Cadastro de um cliente")
	public ResponseEntity<ClienteDTO> post(@Valid @RequestBody ClienteDTO obj) throws URISyntaxException{
		Cliente cliente = service.create(mapper.toEntity(obj));
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(location).body(mapper.toDTO(cliente));
	}
	
	@Override
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE', 'FUNCIONARIO', 'CLIENTE')")
	@Operation(summary = "Edição dos dados de um cliente")
	public ResponseEntity<ClienteDTO> put(@Valid @RequestBody ClienteDTO obj){
		if(service.update(mapper.toEntity(obj))) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@Override
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE', 'CLIENTE')")
	@Operation(summary = "Exclusão de um cliente")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		if(service.delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
