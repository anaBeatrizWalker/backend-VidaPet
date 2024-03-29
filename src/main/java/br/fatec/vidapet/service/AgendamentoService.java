package br.fatec.vidapet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.vidapet.model.Agendamento;
import br.fatec.vidapet.repository.AgendamentoRepository;

@Service
public class AgendamentoService implements ServiceInterface<Agendamento>{
	
	@Autowired
	private AgendamentoRepository repository;
	
	public AgendamentoService() {};
	
	@Override
	public Agendamento create(Agendamento obj) {
		repository.save(obj);
		return obj;
	}
	
	@Override
	public Agendamento findById(Long id) {
		Optional<Agendamento> obj = repository.findById(id);
		return obj.orElse(null);
	}
	
	@Override
	public List<Agendamento> findAll() {
		return repository.listarAgendaOrdenadaPorDataHoraAsc();
	}
	
	@Override
	public boolean update(Agendamento obj) {
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
	
	public List<Agendamento> listarPorFuncionario(Long id){
		return repository.listarPorFuncionario(id);
	}

	public List<Agendamento> listarPorClienteId(Long id){
		return repository.listarPorClienteId(id);
	}
	
	public List<Agendamento> listarPeloDiaAtual(){
		return repository.listarPeloDiaAtual();
	}
	
	public List<Agendamento> listarPelaSemanaAtual(){
		return repository.listarPelaSemanaAtual();
	}
	
	public List<Agendamento> listarPeloMesAtual(){
		return repository.listarPeloMesAtual();
	}
}