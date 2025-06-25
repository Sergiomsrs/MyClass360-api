package org.edu.myclass360.exceptions;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(String message) {
        super(message); // Mensaje de error descriptivo
    }
}
