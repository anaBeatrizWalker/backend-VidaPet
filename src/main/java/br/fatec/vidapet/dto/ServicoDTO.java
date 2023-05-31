package br.fatec.vidapet.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServicoDTO {
	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 50)
	private String nome;
	
	@NotNull
	@Min(1)
	@Max(1000)
	private Float preco;

	private String tipo;
}
