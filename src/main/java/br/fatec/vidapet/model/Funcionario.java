package br.fatec.vidapet.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("funcionario")
public class Funcionario extends Usuario {
	private static final long serialVersionUID = 1L;
	
	public Funcionario() {}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
