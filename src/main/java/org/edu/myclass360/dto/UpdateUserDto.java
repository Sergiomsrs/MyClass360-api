package org.edu.myclass360.dto;

public record UpdateUserDto(
        String name,
        String lastName,
        String secondLastName,
   String email,
   String role,
   Boolean active
) {}
