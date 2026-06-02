package com.rpg.characterservice.controller;

import com.rpg.characterservice.dto.PersonagemRequestDTO;
import com.rpg.characterservice.model.Personagem;
import com.rpg.characterservice.service.PersonagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/personagens")
@RequiredArgsConstructor
public class PersonagemController {

    private final PersonagemService personagemService;

    @PostMapping
    public ResponseEntity<Personagem> criar(@RequestBody PersonagemRequestDTO dto) {
        Personagem p = personagemService.criar(dto.getNome(), dto.getClasse(), dto.getUsuarioId());
        return ResponseEntity.ok(p);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Personagem>> listar(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(personagemService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personagem> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.buscarPorId(id));
    }

    @PostMapping("/{id}/usar-pocao")
    public ResponseEntity<Personagem> usarPocao(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.usarPocao(id));
    }
}