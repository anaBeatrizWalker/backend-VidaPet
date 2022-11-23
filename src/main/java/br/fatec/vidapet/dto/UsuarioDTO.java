package br.fatec.vidapet.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {
	private Long id;
	
	@NotBlank 
	@Size(min = 4, max = 70)
	private String nome;
	
	@NotBlank
	@Email
	@Size(min = 10, max = 70)
	private String email;
	
	@NotBlank
	@CPF
	@Size(min = 11)
	private String cpf;
}
