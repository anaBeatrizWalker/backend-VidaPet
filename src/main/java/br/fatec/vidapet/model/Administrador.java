package br.fatec.vidapet.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("administrador")
public class Administrador extends Usuario {
	private static final long serialVersionUID = 1L;
	
	public Administrador() {}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
