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

import br.fatec.vidapet.dto.AdministradorDTO;
import br.fatec.vidapet.mapper.AdministradorMapper;
import br.fatec.vidapet.model.Administrador;
import br.fatec.vidapet.service.AdministradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/adm")
public class AdministradorController implements ControllerInterface<AdministradorDTO> {

	@Autowired
	private AdministradorService service;

	@Autowired
	private AdministradorMapper mapper;

	@Override
	@GetMapping
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorno da lista de administradores."),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar esse conteúdo."),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema.")
	})
	@Operation(summary = "Retorno da lista de administradores")
	public ResponseEntity<List<AdministradorDTO>> getAll() {
		return ResponseEntity.ok(mapper.toDTO(service.findAll()));
	}

	@Override
	@GetMapping(value = "/{id}")
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@Operation(summary = "Retorno de um administrador")
	public ResponseEntity<AdministradorDTO> getOne(@PathVariable("id") Long id) {
		Administrador obj = service.findById(id);
		if (obj != null) {
			return ResponseEntity.ok(mapper.toDTO(obj));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@PostMapping(produces = "application/json")
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@Operation(summary = "Cadastro de um administrador")
	public ResponseEntity<AdministradorDTO> post(@Valid @RequestBody AdministradorDTO obj) throws URISyntaxException {
		Administrador adm = service.create(mapper.toEntity(obj));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(adm.getId())
				.toUri();
		return ResponseEntity.created(location).body(mapper.toDTO(adm));
	}

	@Override
	@PutMapping
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@Operation(summary = "Edição dos dados de um administrador")
	public ResponseEntity<AdministradorDTO> put(@Valid @RequestBody AdministradorDTO obj) {
		if (service.update(mapper.toEntity(obj))) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PatchMapping(value = "/{id}")
	@Operation(summary = "Atualização parcial de um serviço")
	public ResponseEntity<AdministradorDTO> patch(@PathVariable Long id,
			@Valid @RequestBody AdministradorDTO partialUpdate) {
		Administrador existingAdministrador = service.findById(id);

		if (existingAdministrador == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		// Aplicar atualizações parciais nos campos não nulos do objeto existente
		if (partialUpdate.getNome() != null) {
			existingAdministrador.setNome(partialUpdate.getNome());
		}

		if (partialUpdate.getEmail() != null) {
			existingAdministrador.setEmail(partialUpdate.getEmail());
		}

		if (partialUpdate.getDocumento() != null) {
			existingAdministrador.setDocumento(partialUpdate.getDocumento());
		}

		// if (partialUpdate.getPerfil() != null) {
		// existingAdministrador.setPerfil(partialUpdate.getPerfil());
		// }

		// if (partialUpdate.getLogin() != null) {
		// existingAdministrador.setLogin(partialUpdate.getLogin());
		// }

		// if (partialUpdate.getSenha() != null) {
		// existingAdministrador.setSenha(partialUpdate.getSenha());
		// }

		service.update(existingAdministrador);

		return ResponseEntity.ok(mapper.toDTO(existingAdministrador));
	}

	@Override
	@DeleteMapping(value = "/{id}")
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@Operation(summary = "Exclusão de um administrador")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
