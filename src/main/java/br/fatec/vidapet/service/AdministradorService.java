package br.fatec.vidapet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.vidapet.model.Administrador;
import br.fatec.vidapet.repository.AdministradorRepository;

@Service
public class AdministradorService implements ServiceInterface<Administrador>{
	
	@Autowired
	private AdministradorRepository repository;
	
	public AdministradorService() {};
	
	@Override
	public Administrador create(Administrador obj) {
		repository.save(obj);
		return obj;
	}
	
	@Override
	public Administrador findById(Long id) {
		Optional<Administrador> obj = repository.findById(id);
		return obj.orElse(null);
	}
	
	@Override
	public List<Administrador> findAll(){
		return repository.findAll();
	}
	
	@Override
	public boolean update(Administrador obj) {
		if(repository.existsById(obj.getId())) {
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
