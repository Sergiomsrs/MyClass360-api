package org.edu.myclass360.dto;

public record UserDTO(
        Long id,
        String name,
        String lastName,
        String secondLastName,
        String dni,
        String email,
        String role
) {}
