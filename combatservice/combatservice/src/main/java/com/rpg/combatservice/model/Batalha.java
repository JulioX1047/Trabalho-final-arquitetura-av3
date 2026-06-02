package com.rpg.combatservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "batalhas")
public class Batalha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long personagemId;
    private String nomeInimigo;

    private int hpPersonagem;
    private int hpInimigo;

    @Column(nullable = false)
    private String status; // "EM_ANDAMENTO", "VITORIA", "DERROTA", "FUGA"

    @Column(nullable = false)
    private String turnoAtual; // "JOGADOR" ou "INIMIGO"

    private int turnoNumero;
}