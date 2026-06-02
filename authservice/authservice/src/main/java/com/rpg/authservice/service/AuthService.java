package com.rpg.authservice.service;

import com.rpg.authservice.model.Usuario;
import com.rpg.authservice.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario cadastrar(String username, String password, String role) {
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setRole(role);
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public boolean validarSenha(String senhaDigitada, String senhaCriptografada) {
        return passwordEncoder.matches(senhaDigitada, senhaCriptografada);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}