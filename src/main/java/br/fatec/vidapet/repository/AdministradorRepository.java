package br.fatec.vidapet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fatec.vidapet.model.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

}
