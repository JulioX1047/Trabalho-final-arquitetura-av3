package com.rpg.authservice.controller;

import com.rpg.authservice.dto.LoginRequestDTO;
import com.rpg.authservice.dto.LoginResponseDTO;
import com.rpg.authservice.model.Usuario;
import com.rpg.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody LoginRequestDTO dto) {
        authService.cadastrar(dto.getUsername(), dto.getPassword(), "JOGADOR");
        return ResponseEntity.ok("Usuario cadastrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto) {
        return authService.buscarPorUsername(dto.getUsername())
                .filter(u -> authService.validarSenha(dto.getPassword(), u.getPassword()))
                .map(u -> ResponseEntity.ok(
                        new LoginResponseDTO("token-simulado", u.getUsername(), u.getRole())))
                .orElse(ResponseEntity.status(401).build());
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(authService.listarTodos());
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id) {
        authService.deletar(id);
        return ResponseEntity.ok("Usuario deletado com sucesso!");
    }
}