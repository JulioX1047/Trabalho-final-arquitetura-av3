package com.rpg.combatservice;

import com.rpg.combatservice.model.Batalha;
import com.rpg.combatservice.service.CombatService;
import com.rpg.combatservice.service.MonsterFactory;
import com.rpg.combatservice.model.Monstro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CombatserviceApplicationTests {

	@Autowired
	private CombatService combatService;

	@Autowired
	private MonsterFactory monsterFactory;

	@Test
	void contextLoads() {
	}

	@Test
	void testFactoryGoblin() {
		Monstro m = monsterFactory.criar("GOBLIN");
		assertEquals("GOBLIN", m.getTipo());
		assertEquals(50, m.getHp());
		assertEquals(8, m.getForca());
	}

	@Test
	void testFactoryDragao() {
		Monstro m = monsterFactory.criar("DRAGAO");
		assertEquals("DRAGAO", m.getTipo());
		assertEquals(300, m.getHp());
	}

	@Test
	void testIniciarBatalha() {
		Batalha b = combatService.iniciarBatalha(1L, 100, "GOBLIN");
		assertNotNull(b.getId());
		assertEquals("EM_ANDAMENTO", b.getStatus());
		assertEquals("JOGADOR", b.getTurnoAtual());
		assertEquals(50, b.getHpInimigo());
	}

	@Test
	void testExecutarAtaque() {
		Batalha b = combatService.iniciarBatalha(1L, 100, "GOBLIN");
		var resultado = combatService.executarAtaque(b.getId(), "FISICO", 20, 5);
		assertNotNull(resultado);
		assertTrue(resultado.getDanoGivado() > 0);
	}
}

