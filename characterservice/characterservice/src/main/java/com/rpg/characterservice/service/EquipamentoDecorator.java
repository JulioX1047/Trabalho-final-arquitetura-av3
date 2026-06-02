package com.rpg.characterservice.service;

public class EquipamentoDecorator implements PersonagemDecorator {

    private final int bonusFixo;
    private final String nomeEquipamento;

    public EquipamentoDecorator(int bonusFixo, String nomeEquipamento) {
        this.bonusFixo = bonusFixo;
        this.nomeEquipamento = nomeEquipamento;
    }

    @Override
    public int aplicarBonus(int valorBase) {
        return valorBase + bonusFixo;
    }

    @Override
    public String getDescricao() {
        return nomeEquipamento + " (+" + bonusFixo + " fixo)";
    }
}