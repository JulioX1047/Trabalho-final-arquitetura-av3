package com.rpg.combatservice.service;

import com.rpg.combatservice.model.Monstro;
import com.rpg.combatservice.model.MonstroPrototype;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class MonsterFactory {

    // Padrão Prototype — repositório de protótipos prontos para clonar
    private final Map<String, MonstroPrototype> prototipos = new HashMap<>();

    public MonsterFactory() {
        // Registra os protótipos uma única vez
        prototipos.put("GOBLIN",  new MonstroPrototype("Goblin",        "GOBLIN", 50,  8,  3, 1));
        prototipos.put("ORC",     new MonstroPrototype("Orc Guerreiro", "ORC",   100, 15,  8, 3));
        prototipos.put("DRAGAO",  new MonstroPrototype("Dragao Anciao", "DRAGAO",300, 40, 20,10));
    }

    // Padrão Factory Method — cria monstro sem expor a classe concreta
    public Monstro criar(String tipo) {
        MonstroPrototype proto = prototipos.getOrDefault(
                tipo.toUpperCase(),
                new MonstroPrototype("Monstro Desconhecido", tipo, 60, 10, 5, 1)
        );
        // Clona o protótipo em vez de instanciar do zero
        return proto.clone().toMonstro();
    }
}