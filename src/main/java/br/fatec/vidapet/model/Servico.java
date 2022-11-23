package br.fatec.vidapet.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Servico extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(length=50, nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private Float preco;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private Funcionario funcionario;
}
