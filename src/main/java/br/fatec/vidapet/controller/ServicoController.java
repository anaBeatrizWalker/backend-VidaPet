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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.fatec.vidapet.dto.ServicoDTO;
import br.fatec.vidapet.mapper.ServicoMapper;
import br.fatec.vidapet.model.Servico;
import br.fatec.vidapet.service.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/servicos")
public class ServicoController implements ControllerInterface<ServicoDTO> {

	@Autowired
	private ServicoService service;

	@Autowired
	private ServicoMapper mapper;

	@Override
	@GetMapping
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorno da lista de serviços."),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar esse conteúdo."),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema.")
	})
	@Operation(summary = "Retorno da lista de serviços")
	public ResponseEntity<List<ServicoDTO>> getAll() {
		return ResponseEntity.ok(mapper.toDTO(service.findAll()));
	}

	@Override
	@GetMapping(value = "/{id}")
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@Operation(summary = "Retorno de um serviço")
	public ResponseEntity<ServicoDTO> getOne(@PathVariable("id") Long id) {
		Servico obj = service.findById(id);
		if (obj != null) {
			return ResponseEntity.ok(mapper.toDTO(obj));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@PostMapping
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@Operation(summary = "Cadastro de serviços")
	public ResponseEntity<ServicoDTO> post(@Valid @RequestBody ServicoDTO obj) throws URISyntaxException {
		Servico servico = service.create(mapper.toEntity(obj));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(servico.getId())
				.toUri();
		return ResponseEntity.created(location).body(mapper.toDTO(servico));
	}

	@PatchMapping(value = "/{id}")
	@Operation(summary = "Atualização parcial de um serviço")
	public ResponseEntity<ServicoDTO> patch(@PathVariable Long id, @Valid @RequestBody ServicoDTO partialUpdate) {
		Servico existingServico = service.findById(id);

		if (existingServico == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		// Aplicar atualizações parciais nos campos não nulos do objeto existente
		if (partialUpdate.getNome() != null) {
			existingServico.setNome(partialUpdate.getNome());
		}

		if (partialUpdate.getPreco() != null) {
			existingServico.setPreco(partialUpdate.getPreco());
		}

		if (partialUpdate.getTipo() != null) {
			existingServico.setTipo(partialUpdate.getTipo());
		}

		// Adicione mais verificações e atualizações para outros campos conforme
		// necessário

		// Salvar o objeto atualizado
		service.update(existingServico);

		return ResponseEntity.ok(mapper.toDTO(existingServico));
	}

	@Override
	@PutMapping
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@Operation(summary = "Edição dos dados de um serviço")
	public ResponseEntity<ServicoDTO> put(@Valid @RequestBody ServicoDTO obj) {
		if (service.update(mapper.toEntity(obj))) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@DeleteMapping(value = "/{id}")
	// @PreAuthorize("hasAnyRole('ADMIN')")
	@Operation(summary = "Exclusão de um serviço")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
