package br.fatec.vidapet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fatec.vidapet.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
