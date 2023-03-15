package br.fatec.vidapet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fatec.vidapet.model.Atendente;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, Long>{
    @Query("select a from Atendente a order by a.nome asc")
    List<Atendente> listarAtendentesPorNomeAsc();
}
