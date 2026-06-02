package com.rpg.characterservice.service;

import com.rpg.characterservice.model.Personagem;
import com.rpg.characterservice.repository.PersonagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonagemService {

    private final PersonagemRepository personagemRepository;

    public Personagem criar(String nome, String classe, Long usuarioId) {
        Personagem p = new Personagem();
        p.setNome(nome);
        p.setClasse(classe);
        p.setUsuarioId(usuarioId);
        p.setNivel(1);

        switch (classe.toUpperCase()) {
            case "GUERREIRO" -> { p.setHp(150); p.setMp(30); p.setForca(20); p.setDefesa(15); }
            case "MAGO"      -> { p.setHp(80);  p.setMp(150); p.setForca(25); p.setDefesa(8); }
            case "FURTIVO"   -> { p.setHp(100); p.setMp(60); p.setForca(18); p.setDefesa(10); }
            default          -> { p.setHp(100); p.setMp(100); p.setForca(15); p.setDefesa(10); }
        }

        return personagemRepository.save(p);
    }

    public List<Personagem> listarPorUsuario(Long usuarioId) {
        return personagemRepository.findByUsuarioId(usuarioId);
    }

    public Personagem buscarPorId(Long id) {
        return personagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personagem nao encontrado"));
    }

    public Personagem usarPocao(Long id) {
        Personagem p = buscarPorId(id);
        // Pocao recupera 30 de HP — padrão Decorator aplicado via item
        int hpAtual = p.getHp();
        p.setHp(hpAtual + 30);
        adicionarLog("Pocao usada! +" + 30 + " HP");
        return personagemRepository.save(p);
    }

    private void adicionarLog(String mensagem) {
        System.out.println("[INVENTARIO] " + mensagem);
    }
}