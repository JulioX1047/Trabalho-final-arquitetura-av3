package com.rpg.combatservice.service;

import org.springframework.stereotype.Component;

@Component
public class EstadoNormal implements EstadoPersonagem {

    @Override
    public int aplicarEfeito(int danoRecebido) {
        return danoRecebido; // sem modificação
    }

    @Override
    public String getDescricaoEstado() {
        return "NORMAL";
    }
}