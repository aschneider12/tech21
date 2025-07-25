package br.com.fiap.restaurante.domain.exceptions;

public class SenhaIncorretaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SenhaIncorretaException(String mensagem) {
        super(mensagem);
    }
}
