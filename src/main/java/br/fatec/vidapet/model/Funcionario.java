package br.fatec.vidapet.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("funcionario")
public class Funcionario extends Usuario {
	private static final long serialVersionUID = 1L;
}
