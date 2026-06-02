package com.rpg.combatservice.controller;

import com.rpg.combatservice.dto.AtaqueRequestDTO;
import com.rpg.combatservice.dto.AtaqueResponseDTO;
import com.rpg.combatservice.model.Batalha;
import com.rpg.combatservice.service.CombatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/combate")
@RequiredArgsConstructor
public class CombatController {

    private final CombatService combatService;

    @PostMapping("/iniciar")
    public ResponseEntity<Batalha> iniciar(
            @RequestParam Long personagemId,
            @RequestParam int hpPersonagem,
            @RequestParam String tipoMonstro) {
        return ResponseEntity.ok(
                combatService.iniciarBatalha(personagemId, hpPersonagem, tipoMonstro));
    }

    @PostMapping("/atacar")
    public ResponseEntity<AtaqueResponseDTO> atacar(@RequestBody AtaqueRequestDTO dto,
                                                    @RequestParam int forca,
                                                    @RequestParam int defesaInimigo) {
        return ResponseEntity.ok(
                combatService.executarAtaque(dto.getBatalhaId(), dto.getTipoAtaque(), forca, defesaInimigo));
    }

    @PostMapping("/fugir/{batalhaId}")
    public ResponseEntity<Batalha> fugir(@PathVariable Long batalhaId) {
        return ResponseEntity.ok(combatService.fugirDaBatalha(batalhaId));
    }
}