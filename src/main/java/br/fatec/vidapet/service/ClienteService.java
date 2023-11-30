package br.fatec.vidapet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.fatec.vidapet.model.Cliente;
import br.fatec.vidapet.repository.ClienteRepository;
// import br.fatec.vidapet.security.JWTUtil;

@Service
public class ClienteService implements ServiceInterface<Cliente> {

	@Autowired
	private ClienteRepository repository;

	// @Autowired
	// private BCryptPasswordEncoder passwordEncoder;

	// @Autowired
	// private JWTUtil jwtUtil;

	public ClienteService() {
	};

	@Override
	public Cliente create(Cliente obj) {
		// obj.setSenha(passwordEncoder.encode(obj.getSenha()));
		// Cliente cliente = repository.save(obj);
		// return cliente;
		repository.save(obj);
		return obj;
	}

	@Override
	public Cliente findById(Long id) {
		/*
		 * if (!jwtUtil.authorized(id)) {
		 * throw new
		 * AuthorizationException("Acesso negado! Você não tem permissão para acessar esse conteúdo."
		 * );
		 * }
		 */
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElse(null);
	}

	@Override
	public List<Cliente> findAll() {
		return repository.listarClientesOrdenadosPorNomeAsc();
	}

	@Override
	public boolean update(Cliente obj) {
		if (repository.existsById(obj.getId())) {
			// obj.setSenha(passwordEncoder.encode(obj.getSenha()));
			repository.save(obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
