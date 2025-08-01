package org.edu.myclass360.dto;

public record CreateUserDTO(
        String name,
        String lastName,
        String secondLastName,
        String dni,
        String email,
        String password,
        String role
) {}
