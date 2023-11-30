package br.fatec.vidapet.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.fatec.vidapet.dto.AtendenteDTO;
// import br.fatec.vidapet.exception.AuthorizationException;
import br.fatec.vidapet.mapper.AtendenteMapper;
import br.fatec.vidapet.model.Atendente;
import br.fatec.vidapet.service.AtendenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/atendentes")
public class AtendenteController implements ControllerInterface<AtendenteDTO> {

	@Autowired
	private AtendenteService service;

	@Autowired
	private AtendenteMapper mapper;

	@Override
	@GetMapping
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorno da lista de atendentes."),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar esse conteúdo."),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema.")
	})
	@Operation(summary = "Retorno da lista de atendentes")
	public ResponseEntity<List<AtendenteDTO>> getAll() {
		return ResponseEntity.ok(mapper.toDTO(service.findAll()));
	}

	@Override
	@GetMapping(value = "/{id}")
	@Operation(summary = "Retorno de um atendente")
	public ResponseEntity<AtendenteDTO> getOne(@PathVariable("id") Long id) {
		Atendente obj = service.findById(id);
		if (obj != null) {
			return ResponseEntity.ok(mapper.toDTO(obj));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		/*
		 * try {
		 * Atendente obj = service.findById(id);
		 * if(obj != null) {
		 * return ResponseEntity.ok(mapper.toDTO(obj));
		 * }
		 * return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		 * } catch (AuthorizationException e) {
		 * return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		 * }
		 */
	}

	@Override
	@PostMapping
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@Operation(summary = "Cadastro de um atendente")
	public ResponseEntity<AtendenteDTO> post(@Valid @RequestBody AtendenteDTO obj) throws URISyntaxException {
		Atendente atendente = service.create(mapper.toEntity(obj));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(atendente.getId())
				.toUri();
		return ResponseEntity.created(location).body(mapper.toDTO(atendente));
	}

	@Override
	@PutMapping
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@Operation(summary = "Edição dos dados de um atendente")
	public ResponseEntity<AtendenteDTO> put(@Valid @RequestBody AtendenteDTO obj) {
		if (service.update(mapper.toEntity(obj))) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PatchMapping(value = "/{id}")
	@Operation(summary = "Atualização parcial de um serviço")
	public ResponseEntity<AtendenteDTO> patch(@PathVariable Long id, @Valid @RequestBody AtendenteDTO partialUpdate) {
		Atendente existingAtendente = service.findById(id);

		if (existingAtendente == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		// Aplicar atualizações parciais nos campos não nulos do objeto existente
		if (partialUpdate.getNome() != null) {
			existingAtendente.setNome(partialUpdate.getNome());
		}

		// if (partialUpdate.getEmail() != null) {
		// existingAtendente.setEmail(partialUpdate.getEmail());
		// }

		// if (partialUpdate.getCpf() != null) {
		// existingAtendente.setCpf(partialUpdate.getCpf());
		// }

		// if (partialUpdate.getPerfil() != null) {
		// existingAtendente.setPerfil(partialUpdate.getPerfil());
		// }

		// if (partialUpdate.getLogin() != null) {
		// existingAtendente.setLogin(partialUpdate.getLogin());
		// }

		// if (partialUpdate.getSenha() != null) {
		// existingAtendente.setSenha(partialUpdate.getSenha());
		// }

		service.update(existingAtendente);

		return ResponseEntity.ok(mapper.toDTO(existingAtendente));
	}

	@Override
	@DeleteMapping(value = "/{id}")
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@Operation(summary = "Exclusão de um atendente")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
