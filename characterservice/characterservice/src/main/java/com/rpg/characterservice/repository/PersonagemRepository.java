package com.rpg.characterservice.repository;

import com.rpg.characterservice.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    List<Personagem> findByUsuarioId(Long usuarioId);
}