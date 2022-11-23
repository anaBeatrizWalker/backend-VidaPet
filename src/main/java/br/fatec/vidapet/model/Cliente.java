package br.fatec.vidapet.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cliente extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(length = 70, nullable=false)
	private String nome;
	
	@Column(length = 70, nullable=false, unique = true)
	private String email;
	
	@Column(length = 11, nullable=false)
	private String telefone;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="cliente_id")
	private List<Animal> animais;
}