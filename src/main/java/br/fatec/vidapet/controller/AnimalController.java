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

import br.fatec.vidapet.dto.AnimalDTO;
import br.fatec.vidapet.mapper.AnimalMapper;
import br.fatec.vidapet.model.Animal;
import br.fatec.vidapet.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/animais")
public class AnimalController implements ControllerInterface<AnimalDTO> {

	@Autowired
	private AnimalService service;

	@Autowired
	private AnimalMapper mapper;

	@Override
	@GetMapping
	// @PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE')")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorno da lista de animais."),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar esse conteúdo."),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema.")
	})
	@Operation(summary = "Retorno da lista de animais")
	public ResponseEntity<List<AnimalDTO>> getAll() {
		List<Animal> objts = service.findAll();
		if (objts != null) {
			return ResponseEntity.ok(mapper.toDTO(objts));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@GetMapping(value = "/{id}")
	// @PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE', 'FUNCIONARIO')")
	@Operation(summary = "Retorno de um animal")
	public ResponseEntity<AnimalDTO> getOne(@PathVariable("id") Long id) {
		Animal obj = service.findById(id);
		if (obj != null) {
			return ResponseEntity.ok(mapper.toDTO(obj));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@PostMapping
	// @PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE', 'CLIENTE')")
	@Operation(summary = "Cadastro de um animal")
	public ResponseEntity<AnimalDTO> post(@Valid @RequestBody AnimalDTO obj) throws URISyntaxException {
		Animal animal = service.create(mapper.toEntity(obj));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(animal.getId())
				.toUri();
		return ResponseEntity.created(location).body(mapper.toDTO(animal));
	}

	@PatchMapping(value = "/{id}")
	@Operation(summary = "Atualização parcial de um animal")
	public ResponseEntity<AnimalDTO> patch(@PathVariable Long id, @Valid @RequestBody AnimalDTO partialUpdate) {
		Animal existingAnimal = service.findById(id);

		if (existingAnimal == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		// Aplicar atualizações parciais nos campos não nulos do objeto existente
		if (partialUpdate.getNome() != null) {
			existingAnimal.setNome(partialUpdate.getNome());
		}
		if (partialUpdate.getEspecie() != null) {
			existingAnimal.setEspecie(partialUpdate.getEspecie());
		}
		if (partialUpdate.getRaca() != null) {
			existingAnimal.setRaca(partialUpdate.getRaca());
		}
		if (partialUpdate.getSexo() != null) {
			existingAnimal.setSexo(partialUpdate.getSexo());
		}
		if (partialUpdate.getDataDeNascimento() != null) {
			existingAnimal.setDataDeNascimento(partialUpdate.getDataDeNascimento());
		}
		if (partialUpdate.getPorte() != null) {
			existingAnimal.setPorte(partialUpdate.getPorte());
		}
		if (partialUpdate.getPelagem() != null) {
			existingAnimal.setPelagem(partialUpdate.getPelagem());
		}
		if (partialUpdate.getPeso() != null) {
			existingAnimal.setPeso(partialUpdate.getPeso());
		}
		// Adicione mais verificações e atualizações para outros campos conforme
		// necessário

		// Salvar o objeto atualizado
		service.update(existingAnimal);

		return ResponseEntity.ok(mapper.toDTO(existingAnimal));
	}

	@Override
	@PutMapping
	// @PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE', 'FUNCIONARIO', 'CLIENTE')")
	@Operation(summary = "Edição dos dados de um animal")
	public ResponseEntity<AnimalDTO> put(@Valid @RequestBody AnimalDTO obj) {
		if (service.update(mapper.toEntity(obj))) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@DeleteMapping(value = "/{id}")
	// @PreAuthorize("hasAnyRole('ADMIN', 'ATENDENTE', 'CLIENTE')")
	@Operation(summary = "Exclusão de um animal")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
