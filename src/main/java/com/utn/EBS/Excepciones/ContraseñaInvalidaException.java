package com.utn.EBS.Excepciones;

public class ContraseñaInvalidaException extends RuntimeException {

    public ContraseñaInvalidaException() {
        super("La contraseña no cumple con los requisitos mínimos.");
    }

    public ContraseñaInvalidaException(String message) {
        super(message);
    }
}
