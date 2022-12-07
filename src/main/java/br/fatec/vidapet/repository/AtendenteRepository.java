package br.fatec.vidapet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fatec.vidapet.model.Atendente;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, Long>{

}
