package br.fatec.vidapet.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Usuario extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	
	@Column(length=70, nullable=false)
	private String nome;
	
	@Column(length = 70, nullable=false, unique = true)
	private String email;
	
	@Column(length = 11, nullable=false, unique = true)
	private String cpf;
}
