package br.fatec.vidapet.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "tipo")
public abstract class Usuario extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(length = 70, nullable = false)
	private String nome;

	@Column(length = 70, nullable = true)
	private String email;

	@Column(length = 11, nullable = true)
	private String documento;

	@Column(nullable = true)
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "perfil")
	private Set<Integer> perfil = new HashSet<>();

	public Set<TipoPerfil> getPerfis() {
		return perfil.stream().map(x -> TipoPerfil.toEnum(x))
				.collect(Collectors.toSet());
	}

	public Set<Integer> getPerfisAsInteger() {
		return perfil;
	}

	public void addPerfil(TipoPerfil perfil) {
		this.perfil.add(perfil.getCod());
	}

	@Column(length = 20, nullable = true)
	private String login;

	@Column(nullable = true)
	@Getter(onMethod = @__(@JsonIgnore))
	@Setter(onMethod = @__(@JsonProperty))
	private String senha;

}
