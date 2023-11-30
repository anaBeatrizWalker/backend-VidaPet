package br.fatec.vidapet.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {
	private Long id;

	@NotBlank
	@Size(min = 3, max = 70)
	private String nome;

	// @NotBlank
	@Email
	@Size(min = 10, max = 70)
	private String email;

	// @NotBlank
	// @CPF
	@Size(min = 11)
	private String documento;

	private Set<Integer> perfil;

	// @NotBlank
	@Size(min = 3, max = 20)
	private String login;

	// @NotBlank
	@Size(min = 6, max = 6)
	@Getter(onMethod = @__(@JsonIgnore))
	@Setter(onMethod = @__(@JsonProperty))
	private String senha;

}
