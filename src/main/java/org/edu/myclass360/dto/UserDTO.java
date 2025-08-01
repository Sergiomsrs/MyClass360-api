package org.edu.myclass360.dto;

public record UserDTO(
        Long id,
        String nombre,
        String dni,
        String email,
        String role
) {}
