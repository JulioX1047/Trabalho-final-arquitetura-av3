package com.rpg.combatservice.service;

import com.rpg.combatservice.dto.AtaqueResponseDTO;
import com.rpg.combatservice.model.Batalha;
import com.rpg.combatservice.model.Monstro;
import com.rpg.combatservice.repository.BatalhaRepository;
import com.rpg.combatservice.repository.MonstroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CombatService {

    private final BatalhaRepository batalhaRepository;
    private final MonstroRepository monstroRepository;
    private final MonsterFactory monsterFactory;
    private final PhysicalDamageStrategy physicalDamage;
    private final MagicDamageStrategy magicDamage;
    private final StealthDamageStrategy stealthDamage;

    // Observer — lista de observadores da batalha
    private final List<BatalhaObserver> observers;

    // State — estado atual do personagem
    private EstadoPersonagem estadoAtual = new EstadoNormal();

    public Batalha iniciarBatalha(Long personagemId, int hpPersonagem, String tipoMonstro) {
        Monstro monstro = monsterFactory.criar(tipoMonstro);
        monstroRepository.save(monstro);

        Batalha batalha = new Batalha();
        batalha.setPersonagemId(personagemId);
        batalha.setNomeInimigo(monstro.getNome());
        batalha.setHpPersonagem(hpPersonagem);
        batalha.setHpInimigo(monstro.getHp());
        batalha.setStatus("EM_ANDAMENTO");
        batalha.setTurnoAtual("JOGADOR");
        batalha.setTurnoNumero(1);

        Batalha salva = batalhaRepository.save(batalha);

        // Observer — notifica que batalha iniciou
        notificarObservers("INICIO", salva.getId());
        return salva;
    }

    public AtaqueResponseDTO executarAtaque(Long batalhaId, String tipoAtaque, int forca, int defesaInimigo) {
        Batalha batalha = batalhaRepository.findById(batalhaId)
                .orElseThrow(() -> new RuntimeException("Batalha nao encontrada"));

        // Strategy — seleciona o algoritmo de dano
        DamageStrategy strategy = switch (tipoAtaque.toUpperCase()) {
            case "MAGICO"  -> magicDamage;
            case "FURTIVO" -> stealthDamage;
            default        -> physicalDamage;
        };

        int danoBase = strategy.calcularDano(forca, defesaInimigo);

        // State — aplica efeito do estado atual
        int danoFinal = estadoAtual.aplicarEfeito(danoBase);

        batalha.setHpInimigo(Math.max(0, batalha.getHpInimigo() - danoFinal));

        String status = batalha.getHpInimigo() <= 0 ? "VITORIA" : "EM_ANDAMENTO";
        batalha.setStatus(status);
        batalha.setTurnoAtual("INIMIGO");
        batalha.setTurnoNumero(batalha.getTurnoNumero() + 1);
        batalhaRepository.save(batalha);

        // Observer — notifica sobre o evento
        notificarObservers(status, batalhaId);

        AtaqueResponseDTO response = new AtaqueResponseDTO();
        response.setMensagem("Ataque " + tipoAtaque + " causou " + danoFinal + " de dano! Estado: " + estadoAtual.getDescricaoEstado());
        response.setDanoGivado(danoFinal);
        response.setHpInimigoAtual(batalha.getHpInimigo());
        response.setHpPersonagemAtual(batalha.getHpPersonagem());
        response.setStatusBatalha(status);
        response.setProximoTurno(batalha.getTurnoAtual());

        return response;
    }

    public Batalha fugirDaBatalha(Long batalhaId) {
        Batalha batalha = batalhaRepository.findById(batalhaId)
                .orElseThrow(() -> new RuntimeException("Batalha nao encontrada"));
        batalha.setStatus("FUGA");
        Batalha salva = batalhaRepository.save(batalha);

        // Observer — notifica fuga
        notificarObservers("FUGA", batalhaId);
        return salva;
    }

    // Observer — método para notificar todos os observadores
    private void notificarObservers(String evento, Long batalhaId) {
        observers.forEach(obs -> obs.onBatalhaAtualizada(evento, batalhaId));
    }
}