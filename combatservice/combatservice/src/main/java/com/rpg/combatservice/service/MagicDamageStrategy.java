package com.rpg.combatservice.service;

import org.springframework.stereotype.Component;

@Component
public class MagicDamageStrategy implements DamageStrategy {
    @Override
    public int calcularDano(int forca, int defesa) {
        // Magia ignora metade da defesa
        return Math.max(1, forca - (defesa / 2));
    }
}