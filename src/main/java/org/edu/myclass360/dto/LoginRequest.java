package org.edu.myclass360.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank String dni;
    @NotBlank String password;

    public LoginRequest() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
