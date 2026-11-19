package com.proj.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(int id) {
        super("Usuário de ID " + id + " não encontrado");
    }
}
