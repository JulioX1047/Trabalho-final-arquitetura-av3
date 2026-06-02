package com.rpg.combatservice.service;

public interface BatalhaObserver {
    void onBatalhaAtualizada(String evento, Long batalhaId);
}