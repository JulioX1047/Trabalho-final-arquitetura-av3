package com.rpg.combatservice.service;

import org.springframework.stereotype.Component;

@Component
public class LogObserver implements BatalhaObserver {

    @Override
    public void onBatalhaAtualizada(String evento, Long batalhaId) {
        System.out.println("[LOG] Evento: " + evento + " | Batalha ID: " + batalhaId);
    }
}


