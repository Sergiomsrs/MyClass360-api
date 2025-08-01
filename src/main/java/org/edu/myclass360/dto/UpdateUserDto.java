package org.edu.myclass360.dto;

public record UpdateUserDto(
   String nombre,
   String email,
   String role,
   Boolean active
) {}
