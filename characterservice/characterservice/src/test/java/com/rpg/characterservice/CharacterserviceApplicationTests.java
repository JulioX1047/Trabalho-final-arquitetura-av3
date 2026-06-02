package com.rpg.characterservice;

import com.rpg.characterservice.model.Personagem;
import com.rpg.characterservice.service.PersonagemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CharacterserviceApplicationTests {

	@Autowired
	private PersonagemService personagemService;

	@Test
	void contextLoads() {
	}

	@Test
	void testCriarGuerreiro() {
		Personagem p = personagemService.criar("Aragorn", "GUERREIRO", 1L);
		assertNotNull(p.getId());
		assertEquals("GUERREIRO", p.getClasse());
		assertEquals(150, p.getHp());
		assertEquals(20, p.getForca());
	}

	@Test
	void testCriarMago() {
		Personagem p = personagemService.criar("Gandalf", "MAGO", 1L);
		assertEquals("MAGO", p.getClasse());
		assertEquals(80, p.getHp());
		assertEquals(150, p.getMp());
	}

	@Test
	void testCriarFurtivo() {
		Personagem p = personagemService.criar("Legolas", "FURTIVO", 1L);
		assertEquals("FURTIVO", p.getClasse());
		assertEquals(100, p.getHp());
		assertEquals(18, p.getForca());
	}

	@Test
	void testListarPersonagens() {
		personagemService.criar("Teste1", "GUERREIRO", 2L);
		personagemService.criar("Teste2", "MAGO", 2L);
		assertFalse(personagemService.listarPorUsuario(2L).isEmpty());
	}
}

