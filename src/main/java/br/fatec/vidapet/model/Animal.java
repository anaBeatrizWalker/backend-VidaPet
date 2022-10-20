package br.fatec.vidapet.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Animal extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String tipo;
	private String raça;
	@Enumerated(EnumType.STRING)
	private SexoAnimal sexo;
	private Integer idade;
	@Enumerated(EnumType.STRING)
	private PorteAnimal porte;
	@Enumerated(EnumType.STRING)
	private PelagemAnimal pelagem;
	private Float peso;
	//foto;
	
	public Animal() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getRaça() {
		return raça;
	}

	public void setRaça(String raça) {
		this.raça = raça;
	}

	public SexoAnimal getSexo() {
		return sexo;
	}

	public void setSexo(SexoAnimal sexo) {
		this.sexo = sexo;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public PorteAnimal getPorte() {
		return porte;
	}

	public void setPorte(PorteAnimal porte) {
		this.porte = porte;
	}

	public PelagemAnimal getPelagem() {
		return pelagem;
	}

	public void setPelagem(PelagemAnimal pelagem) {
		this.pelagem = pelagem;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}