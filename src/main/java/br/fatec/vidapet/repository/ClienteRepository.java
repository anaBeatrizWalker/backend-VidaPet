package br.fatec.vidapet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fatec.vidapet.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>  {
	@Query(value="select c from Cliente c order by c.nome asc")
	List<Cliente> listarClientesOrdenadosPorNomeAsc();
}
