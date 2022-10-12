package org.senai.prova.repository;

import org.senai.prova.model.Camiseta;
import org.senai.prova.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CamisetaRepository extends JpaRepository<Camiseta, Long> {
    List<Camiseta> findByDisponibilidade(boolean disponibilidade);
    List<Camiseta> findByCategoria(Categoria categoria);
}
