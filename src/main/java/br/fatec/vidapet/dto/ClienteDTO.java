package br.fatec.vidapet.dto;

import java.util.List; 

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.fatec.vidapet.model.Animal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO {
	private Long id;
	
	@NotBlank 
	@Size(min = 4, max = 70)
	private String nome;
	
	@NotBlank
	@Email
	@Size(min = 10, max = 70)
	private String email;
	
	@NotBlank
	@Size(min = 11)
	private String telefone;
	
	@NotNull
	private List<Animal> animais;
}
