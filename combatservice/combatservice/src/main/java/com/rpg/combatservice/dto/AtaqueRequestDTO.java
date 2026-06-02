package com.rpg.combatservice.dto;

import lombok.Data;

@Data
public class AtaqueRequestDTO {
    private Long batalhaId;
    private Long personagemId;
    private String tipoAtaque; // "FISICO", "MAGICO", "FURTIVO"
}