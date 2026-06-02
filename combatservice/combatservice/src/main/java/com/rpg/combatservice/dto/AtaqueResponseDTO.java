package com.rpg.combatservice.dto;

import lombok.Data;

@Data
public class AtaqueResponseDTO {
    private String mensagem;
    private int danoGivado;
    private int hpPersonagemAtual;
    private int hpInimigoAtual;
    private String statusBatalha;
    private String proximoTurno;
}