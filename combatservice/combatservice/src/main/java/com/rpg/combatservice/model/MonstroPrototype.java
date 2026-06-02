package com.rpg.combatservice.model;

import lombok.Data;

@Data
public class MonstroPrototype implements Cloneable {

    private String nome;
    private String tipo;
    private int hp;
    private int forca;
    private int defesa;
    private int nivel;

    public MonstroPrototype(String nome, String tipo, int hp, int forca, int defesa, int nivel) {
        this.nome = nome;
        this.tipo = tipo;
        this.hp = hp;
        this.forca = forca;
        this.defesa = defesa;
        this.nivel = nivel;
    }

    // Padrão Prototype — clona o monstro sem instanciar do zero
    @Override
    public MonstroPrototype clone() {
        try {
            return (MonstroPrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Erro ao clonar monstro", e);
        }
    }

    public Monstro toMonstro() {
        Monstro m = new Monstro();
        m.setNome(this.nome);
        m.setTipo(this.tipo);
        m.setHp(this.hp);
        m.setForca(this.forca);
        m.setDefesa(this.defesa);
        m.setNivel(this.nivel);
        return m;
    }
}