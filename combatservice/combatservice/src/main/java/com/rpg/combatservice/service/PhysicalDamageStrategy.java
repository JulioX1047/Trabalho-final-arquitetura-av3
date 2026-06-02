package com.rpg.combatservice.service;

import org.springframework.stereotype.Component;

@Component
public class PhysicalDamageStrategy implements DamageStrategy {
    @Override
    public int calcularDano(int forca, int defesa) {
        return Math.max(1, forca - defesa);
    }
}