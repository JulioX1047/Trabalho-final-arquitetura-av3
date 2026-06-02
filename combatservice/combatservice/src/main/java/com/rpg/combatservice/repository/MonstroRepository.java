package com.rpg.combatservice.repository;

import com.rpg.combatservice.model.Monstro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MonstroRepository extends JpaRepository<Monstro, Long> {
    List<Monstro> findByTipo(String tipo);
}