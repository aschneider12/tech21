package br.com.fiap.restaurante.entities;

import java.time.LocalDate;


/*
Entidade do usuário, utilizar JPA?
 */
public class Usuario {

        private String nome;
        private String email;
        private String login;
        private String senha;
        private LocalDate dataUltimaAlteracao;
        private Endereco endereco;
        private TipoUsuario tipoUsuario;

}
