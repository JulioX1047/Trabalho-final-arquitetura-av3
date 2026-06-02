package com.rpg.combatservice.service;

import org.springframework.stereotype.Component;

@Component
public class EstadoEnvenenado implements EstadoPersonagem {

    @Override
    public int aplicarEfeito(int danoRecebido) {
        // Envenenado recebe 20% a mais de dano
        return (int)(danoRecebido * 1.2);
    }

    @Override
    public String getDescricaoEstado() {
        return "ENVENENADO";
    }
}

