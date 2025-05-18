package br.com.fiap.restaurante.exceptions;

public class LoginExistsException extends RuntimeException {
    public LoginExistsException(String message) {

        super(message);
    }
}
