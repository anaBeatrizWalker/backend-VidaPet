package br.fatec.vidapet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.fatec.vidapet.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
    @Query("select f from Funcionario f order by f.nome asc")
    List<Funcionario> listarFuncionariosPorNomeAsc();
}
