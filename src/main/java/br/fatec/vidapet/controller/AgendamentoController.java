package br.fatec.vidapet.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
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
// import br.fatec.vidapet.model.Animal;
// import br.fatec.vidapet.model.Funcionario;
import br.fatec.vidapet.service.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/agenda")
public class AgendamentoController implements ControllerInterface<AgendamentoDTO> {

	@Autowired
	private AgendamentoService service;

	@Autowired
	private AgendamentoMapper mapper;

	@Override
	@GetMapping
	// @PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE')")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorno da lista de agendamentos."),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar esse conteúdo."),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema.")
	})
	@Operation(summary = "Retorno da lista de agendamentos")
	public ResponseEntity<List<AgendamentoDTO>> getAll() {
		List<Agendamento> objts = service.findAll();
		if (objts != null) {
			return ResponseEntity.ok(mapper.toDTO(objts));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@GetMapping(value = "/{id}")
	// @PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE')")
	@Operation(summary = "Retorno de um agendamento")
	public ResponseEntity<AgendamentoDTO> getOne(@PathVariable("id") Long id) {
		Agendamento obj = service.findById(id);
		if (obj != null) {
			return ResponseEntity.ok(mapper.toDTO(obj));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@PostMapping
	// @PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE')")
	@Operation(summary = "Cadastro de um agendamento")
	public ResponseEntity<AgendamentoDTO> post(@Valid @RequestBody AgendamentoDTO obj) throws URISyntaxException {
		Agendamento agendamento = service.create(mapper.toEntity(obj));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(agendamento.getId()).toUri();
		return ResponseEntity.created(location).body(mapper.toDTO(agendamento));
	}

	@Override
	@PutMapping
	// @PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE', 'FUNCIONARIO')")
	@Operation(summary = "Edição dos dados de um agendamento")
	public ResponseEntity<AgendamentoDTO> put(@Valid @RequestBody AgendamentoDTO obj) {
		if (service.update(mapper.toEntity(obj))) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@DeleteMapping(value = "/{id}")
	// @PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE')")
	@Operation(summary = "Exclusão de um agendamento")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping(value = "/funcionario/agenda/{id}")
	// @PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE', 'FUNCIONARIO')")
	@Operation(summary = "Retorno de uma lista de agendamentos por funcionario")
	public ResponseEntity<List<AgendamentoDTO>> listarPorFuncionario(@PathVariable("id") Long id) {
		List<Agendamento> obj = service.listarPorFuncionario(id);
		if (obj != null)
			return ResponseEntity.ok(mapper.toDTO(obj));
		return ResponseEntity.notFound().build();
	}

	@GetMapping(value = "/cliente/agenda/{id}")
	// @PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE', 'FUNCIONARIO')")
	@Operation(summary = "Retorno de uma lista de agendamentos por cliente")
	public ResponseEntity<List<AgendamentoDTO>> listarPorClienteId(@PathVariable("id") Long id) {
		List<Agendamento> obj = service.listarPorClienteId(id);
		if (obj != null)
			return ResponseEntity.ok(mapper.toDTO(obj));
		return ResponseEntity.notFound().build();
	}

	@GetMapping(value = "/dia_atual")
	// @PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE')")
	@Operation(summary = "Retorno de uma lista de agendamentos do dia atual")
	public ResponseEntity<List<AgendamentoDTO>> listarPeloDiaAtual() {
		List<Agendamento> obj = service.listarPeloDiaAtual();
		if (obj != null)
			return ResponseEntity.ok(mapper.toDTO(obj));
		return ResponseEntity.notFound().build();
	}

	@GetMapping(value = "/semana_atual")
	// @PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE')")
	@Operation(summary = "Retorno de uma lista de agendamentos da semana atual")
	public ResponseEntity<List<AgendamentoDTO>> listarPelaSemanaAtual() {
		List<Agendamento> obj = service.listarPelaSemanaAtual();
		if (obj != null)
			return ResponseEntity.ok(mapper.toDTO(obj));
		return ResponseEntity.notFound().build();
	}

	@GetMapping(value = "/mes_atual")
	// @PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE')")
	@Operation(summary = "Retorno de uma lista de agendamentos do mês atual")
	public ResponseEntity<List<AgendamentoDTO>> listarPeloMesAtual() {
		List<Agendamento> obj = service.listarPeloMesAtual();
		if (obj != null)
			return ResponseEntity.ok(mapper.toDTO(obj));
		return ResponseEntity.notFound().build();
	}
}
