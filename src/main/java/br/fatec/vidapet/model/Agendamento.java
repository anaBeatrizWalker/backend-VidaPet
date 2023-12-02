package br.fatec.vidapet.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Agendamento extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@OneToOne(cascade = { CascadeType.ALL })
	private Funcionario funcionario;

	@Column(length = 100)
	private String observacao;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Calendar data;

	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "GMT-3")
	private Date horario;

	@ManyToOne(fetch = FetchType.EAGER)
	private Animal animal;
}
