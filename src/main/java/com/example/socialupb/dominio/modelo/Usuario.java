package com.example.socialupb.dominio.modelo;

import com.example.socialupb.dominio.exception.OperationException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {
    private String nombre;
    private String email;
    private String password;


    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;

        validar();
    }

    private void validar() {
        validarNombre();
        validarEmail();
        validarPassword();
    }

    private void validarNombre() {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new OperationException("El nombre no puede estar vacío");
        }
        if (nombre.length() < 2) {
            throw new OperationException("El nombre debe tener al menos 2 caracteres");
        }
    }

    private void validarEmail() {
        if (email == null || email.isBlank()) {
            throw new OperationException("El email es obligatorio");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new OperationException("Email inválido");
        }
    }

    private void validarPassword() {
        if (password == null || password.isBlank()) {
            throw new OperationException("La contraseña es obligatoria");
        }
        if (password.length() < 8) {
            throw new OperationException("La contraseña debe tener mínimo 8 caracteres");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new OperationException("Debe contener una mayúscula");
        }
        if (!password.matches(".*[0-9].*")) {
            throw new OperationException("Debe contener un número");
        }
    }
}