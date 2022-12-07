package br.fatec.vidapet.dto;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.fatec.vidapet.model.Animal;
import br.fatec.vidapet.model.Funcionario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AgendamentoDTO{
	private Long id;
	
	@NotNull
	private Funcionario funcionario;
	
	@Size(min = 4, max = 100)
	private String observacao;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Calendar data;
	
	@NotNull
	@Temporal(TemporalType.TIME)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm", timezone="GMT-3")
	private Date horario;
	
	@NotNull
	private Animal animal;
}
