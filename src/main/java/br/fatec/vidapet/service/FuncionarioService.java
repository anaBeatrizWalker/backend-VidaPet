package br.fatec.vidapet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.fatec.vidapet.exception.AuthorizationException;
import br.fatec.vidapet.model.Funcionario;
import br.fatec.vidapet.repository.FuncionarioRepository;
// import br.fatec.vidapet.security.JWTUtil;

@Service
public class FuncionarioService implements ServiceInterface<Funcionario> {

	@Autowired
	private FuncionarioRepository repository;

	// @Autowired
	// private BCryptPasswordEncoder passwordEncoder;

	// @Autowired
	// private JWTUtil jwtUtil;

	public FuncionarioService() {
	}

	@Override
	public Funcionario create(Funcionario obj) {
		// obj.setSenha(passwordEncoder.encode(obj.getSenha()));
		// Funcionario funcionario = repository.save(obj);
		// return funcionario;
		repository.save(obj);
		return obj;
	}

	@Override
	public Funcionario findById(Long id) throws AuthorizationException {
		/*
		 * if (!jwtUtil.authorized(id)) {
		 * throw new
		 * AuthorizationException("Acesso negado! Você não tem permissão para acessar esse conteúdo."
		 * );
		 * }
		 */
		Optional<Funcionario> obj = repository.findById(id);
		return obj.orElse(null);
	}

	@Override
	public List<Funcionario> findAll() {
		return repository.listarFuncionariosPorNomeAsc();
	}

	@Override
	public boolean update(Funcionario obj) {
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
