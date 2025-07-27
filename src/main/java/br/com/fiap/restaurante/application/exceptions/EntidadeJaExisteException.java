package br.com.fiap.restaurante.application.exceptions;

public class EntidadeJaExisteException extends RuntimeException {

    public EntidadeJaExisteException(String entidade, String nomeJaExiste){
        super(entidade+" <"+nomeJaExiste+"> já existe!");
    }
}
