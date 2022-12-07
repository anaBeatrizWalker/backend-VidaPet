package br.fatec.vidapet.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.fatec.vidapet.model.TipoPerfil;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String login;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl() {}

	public UserDetailsImpl(Long id, String login, String senha, Set<TipoPerfil> perfis) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao()))
				.collect(Collectors.toList());
	}
	public Long getId() {
		return id;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		return senha;
	}
	@Override
	public String getUsername() {
		return login;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	public boolean hasRole(TipoPerfil perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}
}
