// package br.fatec.vidapet.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import
// org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import br.fatec.vidapet.model.Usuario;
// import br.fatec.vidapet.repository.UsuarioRepository;
// import br.fatec.vidapet.security.UserDetailsImpl;

// @Service
// public class UserDetailsServiceImpl implements UserDetailsService {

// @Autowired
// private UsuarioRepository repository;

// @Override
// public UserDetails loadUserByUsername(String username) throws
// UsernameNotFoundException {
// Usuario usuario = repository.findByLogin(username);
// if (usuario == null) {
// throw new UsernameNotFoundException(username);
// }
// return new UserDetailsImpl(usuario.getId(), usuario.getLogin(),
// usuario.getSenha(), usuario.getPerfis());
// }
// }
