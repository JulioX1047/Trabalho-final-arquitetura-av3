package com.rpg.characterservice.service;

public class BuffDecorator implements PersonagemDecorator {

    private final int bonusPercentual;
    private final String descricao;

    public BuffDecorator(int bonusPercentual, String descricao) {
        this.bonusPercentual = bonusPercentual;
        this.descricao = descricao;
    }

    @Override
    public int aplicarBonus(int valorBase) {
        return valorBase + (valorBase * bonusPercentual / 100);
    }

    @Override
    public String getDescricao() {
        return descricao + " (+" + bonusPercentual + "%)";
    }
}