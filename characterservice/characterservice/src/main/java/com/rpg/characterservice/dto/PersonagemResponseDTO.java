package com.rpg.characterservice.dto;

import lombok.Data;

@Data
public class PersonagemResponseDTO {
    private Long id;
    private String nome;
    private String classe;
    private int hp;
    private int mp;
    private int forca;
    private int defesa;
    private int nivel;
}