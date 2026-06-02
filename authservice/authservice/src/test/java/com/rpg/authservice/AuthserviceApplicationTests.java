package com.rpg.authservice;

import com.rpg.authservice.service.AuthService;
import com.rpg.authservice.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthserviceApplicationTests {

	@Autowired
	private AuthService authService;

	@Test
	void contextLoads() {
	}

	@Test
	void testCadastrarUsuario() {
		Usuario u = authService.cadastrar("testUser", "senha123", "JOGADOR");
		assertNotNull(u.getId());
		assertEquals("testUser", u.getUsername());
		assertEquals("JOGADOR", u.getRole());
	}

	@Test
	void testValidarSenha() {
		authService.cadastrar("userSenha", "minhasenha", "JOGADOR");
		assertTrue(authService.buscarPorUsername("userSenha").isPresent());
	}

	@Test
	void testListarUsuarios() {
		authService.cadastrar("user1", "senha1", "JOGADOR");
		authService.cadastrar("user2", "senha2", "JOGADOR");
		assertFalse(authService.listarTodos().isEmpty());
	}
}