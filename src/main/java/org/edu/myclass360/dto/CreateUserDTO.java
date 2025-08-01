package org.edu.myclass360.dto;

public record CreateUserDTO(
        String nombre,
        String dni,
        String email,
        String password,
        String role
) {}
