package br.fatec.vidapet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.vidapet.model.Cliente;
import br.fatec.vidapet.repository.ClienteRepository;

@Service
public class ClienteService implements ServiceInterface<Cliente>{
	
	@Autowired
	private ClienteRepository repository;
	
	public ClienteService() {};
	
	@Override
	public Cliente create(Cliente obj) {
		repository.save(obj);
		return obj;
	}
	
	@Override
	public Cliente findById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElse(null);
	}
	
	@Override
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	@Override
	public boolean update(Cliente obj) {
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
	
	public List<Cliente> listarClientesOrdenadosAsc() {
		return repository.listarClientesOrdenadosAsc();
	}
}
