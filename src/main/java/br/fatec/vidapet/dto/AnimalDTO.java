package br.fatec.vidapet.dto;

import java.util.Calendar;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@Size(min = 3, max = 50)
	private String nome;
	
	@NotBlank 
	@Size(min = 3, max = 50)
	private String especie;
	
	@Size(min = 3, max = 50)
	private String raça;
	
	@NotNull
	private SexoAnimal sexo;
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Calendar dataDeNascimento;
	
	@NotNull
	private PorteAnimal porte;
	
	@NotNull
	private PelagemAnimal pelagem;
	
	@Min(1)
	@Max(100)
	private Float peso;
	
	//foto;
	
	@NotNull
	@Getter(onMethod = @__(@JsonIgnore))
	@Setter(onMethod = @__(@JsonProperty))
	private Cliente cliente;
}
