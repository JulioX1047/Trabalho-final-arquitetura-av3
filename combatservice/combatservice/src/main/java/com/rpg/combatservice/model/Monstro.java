package com.rpg.combatservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "monstros")
public class Monstro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo; // "GOBLIN", "ORC", "DRAGAO"

    private int hp;
    private int forca;
    private int defesa;
    private int nivel;
}