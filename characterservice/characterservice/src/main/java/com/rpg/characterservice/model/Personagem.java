package com.rpg.characterservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "personagens")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String classe; // "GUERREIRO", "MAGO", "FURTIVO"

    private int hp;
    private int mp;
    private int forca;
    private int defesa;
    private int nivel;

    @Column(nullable = false)
    private Long usuarioId; // referência ao usuário dono do personagem
}