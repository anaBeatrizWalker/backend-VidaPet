package br.fatec.vidapet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fatec.vidapet.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>  {
	@Query(value="select * from animal order by nome asc;", nativeQuery=true)
	List<Animal> listarAnimaisOrdenadosPorNomeAsc();
}
