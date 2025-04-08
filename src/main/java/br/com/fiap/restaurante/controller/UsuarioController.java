package br.com.fiap.restaurante.controller;

import br.com.fiap.restaurante.entities.Usuario;
import br.com.fiap.restaurante.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
    Camada de comunicação com a web.
*/
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    //TODO - cadastrar usuario

    //TODO -alterar dados do usuario
    //TODO - alterarSenha
    //TODO - validacao do login
}
