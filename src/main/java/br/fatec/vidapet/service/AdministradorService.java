package br.fatec.vidapet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.fatec.vidapet.model.Administrador;
import br.fatec.vidapet.repository.AdministradorRepository;

@Service
public class AdministradorService implements ServiceInterface<Administrador>{
	
	@Autowired
	private AdministradorRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public AdministradorService() {};

	@Override
	public Administrador create(Administrador obj) { 
		obj.setSenha(passwordEncoder.encode(obj.getSenha()));
		Administrador adm = repository.save(obj); 
		return adm;
	}
	
	@Override
	public Administrador findById(Long id) {
		Optional<Administrador> obj = repository.findById(id);
		return obj.orElse(null);
	}
	
	@Override
	public List<Administrador> findAll(){
		return repository.listarAdministradoresPorNomeAsc();
	}
	
	@Override
	public boolean update(Administrador obj) {
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
