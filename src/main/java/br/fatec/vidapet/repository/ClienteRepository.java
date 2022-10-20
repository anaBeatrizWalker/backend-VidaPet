package br.fatec.vidapet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.fatec.vidapet.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>  {

}
