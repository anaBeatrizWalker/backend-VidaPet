package br.fatec.vidapet.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.fatec.vidapet.model.Cliente;
import br.fatec.vidapet.model.PelagemAnimal;
import br.fatec.vidapet.model.PorteAnimal;
import br.fatec.vidapet.model.SexoAnimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnimalDTO {
	private Long id;
	
	@NotBlank
	@Size(min = 4, max = 50)
	private String nome;
	
	@NotBlank 
	@Size(min = 4, max = 50)
	private String especie;
	
	@Size(min = 3, max = 50)
	private String raça;
	
	@NotNull
	private SexoAnimal sexo;
	
	@Min(1)
	@Max(20)
	private Integer idade;
	
	@NotNull
	private PorteAnimal porte;
	
	@NotNull
	private PelagemAnimal pelagem;
	
	@Min(1)
	@Max(50)
	private Float peso;
	
	//foto;
	
	@NotNull
	@Getter(onMethod = @__(@JsonIgnore))
	@Setter(onMethod = @__(@JsonProperty))
	private Cliente cliente;
}
