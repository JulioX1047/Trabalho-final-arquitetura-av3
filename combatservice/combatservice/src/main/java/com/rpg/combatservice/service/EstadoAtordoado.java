package com.rpg.combatservice.service;

import org.springframework.stereotype.Component;

@Component
public class EstadoAtordoado implements EstadoPersonagem {

    @Override
    public int aplicarEfeito(int danoRecebido) {
        // Atordoado recebe 50% a mais de dano
        return (int)(danoRecebido * 1.5);
    }

    @Override
    public String getDescricaoEstado() {
        return "ATORDOADO";
    }
}