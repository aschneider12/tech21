package br.com.fiap.restaurante.application.exceptions;

public class SenhaIncorretaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SenhaIncorretaException(String mensagem) {
        super(mensagem);
    }
}
