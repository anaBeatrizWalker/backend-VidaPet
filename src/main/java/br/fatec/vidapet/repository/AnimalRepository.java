package br.fatec.vidapet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.fatec.vidapet.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>  {

}
