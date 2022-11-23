package br.fatec.vidapet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fatec.vidapet.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
	@Query(value="select * from servico order by nome asc;", nativeQuery=true)
	List<Servico> listarServicosOrdenadosAsc();
}
