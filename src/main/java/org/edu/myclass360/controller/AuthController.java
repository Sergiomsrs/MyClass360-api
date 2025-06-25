package org.edu.myclass360.controller;

import org.edu.myclass360.dto.LoginRequest;
import org.edu.myclass360.exceptions.CredencialesInvalidasException;
import org.edu.myclass360.exceptions.UsuarioNoEncontradoException;
import org.edu.myclass360.model.User;
import org.edu.myclass360.repository.UserRepository;
import org.edu.myclass360.security.JwtUtils;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        // 1. Validación de entrada
        if (loginRequest.getDni() == null || loginRequest.getPassword() == null) {
            throw new IllegalArgumentException("DNI y contraseña son obligatorios");
        }

        // 2. Buscar usuario (manejo seguro con orElseThrow)
        User user = userRepository.findByDni(loginRequest.getDni())
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        // 3. Validar contraseña
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new CredencialesInvalidasException("Contraseña incorrecta");
        }

        // 4. Generar token JWT
        String token = jwtUtils.generateToken(user.getDni(), user.getRole().name());

        // 5. Retornar respuesta estructurada
        return ResponseEntity.ok(Map.of(
                "token", token,
                "role", user.getRole().name()
        ));
    }
}
