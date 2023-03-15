package br.fatec.vidapet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.vidapet.model.Animal;
import br.fatec.vidapet.repository.AnimalRepository;

@Service
public class AnimalService implements ServiceInterface<Animal>{
	
	@Autowired
	private AnimalRepository repository;
	
	public AnimalService() {};
	
	@Override
	public Animal create(Animal obj) {
		repository.save(obj);
		return obj;
	}
	
	@Override
	public Animal findById(Long id) {
		Optional<Animal> obj = repository.findById(id);
		return obj.orElse(null);
	}
	
	@Override
	public List<Animal> findAll() {
		return repository.findAll();
	}
	
	@Override
	public boolean update(Animal obj) {
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
