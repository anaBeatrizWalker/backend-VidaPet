package br.fatec.vidapet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.fatec.vidapet.model.Funcionario;
import br.fatec.vidapet.repository.FuncionarioRepository;

@Service
public class FuncionarioService implements ServiceInterface<Funcionario>{
	
	@Autowired
	private FuncionarioRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public FuncionarioService() {}
	
	@Override
	public Funcionario create(Funcionario obj) { 
		obj.setSenha(passwordEncoder.encode(obj.getSenha()));
		Funcionario funcionario = repository.save(obj); 
		return funcionario;
	}
	
	@Override
	public Funcionario findById(Long id) {
		Optional<Funcionario> obj = repository.findById(id);
		return obj.orElse(null);
	}
	
	@Override
	public List<Funcionario> findAll(){
		return repository.findAll();
	}
	
	@Override
	public boolean update(Funcionario obj) {
		if(repository.existsById(obj.getId())) {
			obj.setSenha(passwordEncoder.encode(obj.getSenha()));
			repository.save(obj);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delete(Long id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}

}
