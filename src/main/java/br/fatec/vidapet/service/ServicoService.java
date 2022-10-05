package br.fatec.vidapet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.vidapet.model.Servico;
import br.fatec.vidapet.repository.ServicoRepository;

@Service
public class ServicoService implements ServiceInterface<Servico>{
	
	@Autowired
	private ServicoRepository repository;
	
	public ServicoService() {};
	
	@Override
	public Servico create(Servico obj) {
		repository.save(obj);
		return obj;
	}
	
	@Override
	public Servico findById(Long id) {
		Optional<Servico> obj = repository.findById(id);
		return obj.orElse(null);
	}
	
	@Override
	public List<Servico> findAll() {
		return repository.findAll();
	}
	
	@Override
	public boolean update(Servico obj) {
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
