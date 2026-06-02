package com.rpg.characterservice.repository;

import com.rpg.characterservice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByPersonagemId(Long personagemId);
}