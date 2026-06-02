package com.rpg.combatservice.repository;

import com.rpg.combatservice.model.Batalha;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BatalhaRepository extends JpaRepository<Batalha, Long> {
    List<Batalha> findByPersonagemId(Long personagemId);
}