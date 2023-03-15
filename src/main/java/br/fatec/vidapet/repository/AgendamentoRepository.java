package br.fatec.vidapet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fatec.vidapet.model.Agendamento;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
	@Query("select a from Agendamento a join a.funcionario f where f.login=?1 order by a.data, a.horario asc")
	List<Agendamento> listarPorFuncionario(String login);
	
	@Query(value="select * from agendamento where data = CURRENT_DATE() order by data, horario asc;", nativeQuery=true)
	List<Agendamento> listarPeloDiaAtual();
	
	@Query(value="select * from agendamento where yearweek(data, 1) = yearweek(curdate(), 1) order by data, horario asc;", nativeQuery=true)
	List<Agendamento> listarPelaSemanaAtual();
	
	@Query(value="select * from agendamento where month(data) = month(now()) order by data, horario asc;", nativeQuery=true)
	List<Agendamento> listarPeloMesAtual();
	
	@Query(value="select a from Agendamento a order by a.data, a.horario asc")
	List<Agendamento> listarAgendaOrdenadaPorDataHoraAsc();
}
