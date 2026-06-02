package com.rpg.combatservice.service;

import org.springframework.stereotype.Component;

@Component
public class StealthDamageStrategy implements DamageStrategy {
    @Override
    public int calcularDano(int forca, int defesa) {
        // Furtivo ignora toda a defesa e tem chance de crítico
        int danoBase = forca;
        boolean critico = Math.random() < 0.3; // 30% de chance
        return critico ? danoBase * 2 : danoBase;
    }
}