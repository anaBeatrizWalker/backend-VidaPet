package br.fatec.vidapet.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Animal extends AbstractEntity {
	@Getter
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Column(length = 50, nullable = false)
	private String nome;

	@Getter
	@Setter
	@Column(length = 50, nullable = true)
	private String especie;

	@Getter
	@Setter
	@Column(length = 50, nullable = true)
	private String raca;

	@Getter
	@Setter
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private SexoAnimal sexo;

	@Getter
	@Setter
	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Calendar dataDeNascimento;

	@Getter
	@Setter
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private PorteAnimal porte;

	@Getter
	@Setter
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private PelagemAnimal pelagem;

	@Getter
	@Setter
	@Column(nullable = true)
	private Float peso;

	// foto;

	@Getter(onMethod = @__(@JsonIgnore))
	@Setter(onMethod = @__(@JsonProperty))
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
}