package br.com.fiap.restaurante.exceptions;

public class SenhaIncorretaException extends RuntimeException{
    public SenhaIncorretaException(String message) {
        super(message);
    }
}
