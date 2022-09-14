package br.senai.sc.edu.aulaapi.repository;

import br.senai.sc.edu.aulaapi.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByNome(String nome);

    List<Aluno> findByEndereco(String endereco);

    List<Aluno> findByNomeIgnoreCaseContains(String nome);

    @Query("SELECT COUNT(a) from Aluno a")
    Integer quantidadeAlunos();

}
