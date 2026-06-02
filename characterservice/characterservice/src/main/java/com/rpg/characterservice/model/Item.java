package com.rpg.characterservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "itens")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo; // "ARMA", "ARMADURA", "POCAO"

    private int bonus;
    private double peso;

    @ManyToOne
    @JoinColumn(name = "personagem_id")
    private Personagem personagem;
}