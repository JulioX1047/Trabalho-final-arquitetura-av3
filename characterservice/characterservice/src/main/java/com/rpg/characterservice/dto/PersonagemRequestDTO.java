package com.rpg.characterservice.dto;

import lombok.Data;

@Data
public class PersonagemRequestDTO {
    private String nome;
    private String classe;
    private Long usuarioId;
}