package br.fatec.vidapet.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Servico extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private Float preco;
	@OneToOne(cascade = {CascadeType.ALL})
	private Funcionario funcionario;
	
	public Servico() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
