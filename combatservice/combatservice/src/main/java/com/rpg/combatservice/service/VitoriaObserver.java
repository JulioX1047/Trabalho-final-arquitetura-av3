package com.rpg.combatservice.service;

import org.springframework.stereotype.Component;

@Component
public class VitoriaObserver implements BatalhaObserver {

    @Override
    public void onBatalhaAtualizada(String evento, Long batalhaId) {
        if ("VITORIA".equals(evento)) {
            System.out.println("[VITORIA] Batalha " + batalhaId + " finalizada com vitoria!");
        } else if ("DERROTA".equals(evento)) {
            System.out.println("[DERROTA] Batalha " + batalhaId + " finalizada com derrota!");
        } else if ("FUGA".equals(evento)) {
            System.out.println("[FUGA] Jogador fugiu da batalha " + batalhaId);
        }
    }
}