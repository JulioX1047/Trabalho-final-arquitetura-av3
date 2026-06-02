package com.rpg.combatservice.service;

public interface EstadoPersonagem {
    int aplicarEfeito(int danoRecebido);
    String getDescricaoEstado();
}