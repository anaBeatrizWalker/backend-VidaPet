package br.fatec.vidapet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.fatec.vidapet.model.Atendente;
import br.fatec.vidapet.repository.AtendenteRepository;

@Service
public class AtendenteService implements ServiceInterface<Atendente>{

	@Autowired
	private AtendenteRepository repository;
	
	public AtendenteService() {};
	
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder; 

	@Override 
	public Atendente create(Atendente obj) { 
		obj.setSenha(passwordEncoder.encode(obj.getSenha())); 
		Atendente atendente = repository.save(obj); 
		return atendente;
	} 
	
	@Override
	public Atendente findById(Long id) {
		Optional<Atendente> obj = repository.findById(id);
		if (obj.isPresent()) 
			return obj.get(); 
		return null; 
	}
	
	@Override
	public List<Atendente> findAll(){
		return repository.findAll();
	}
	
	@Override
	public boolean update(Atendente obj) {
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
