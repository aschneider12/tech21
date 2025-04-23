package br.com.fiap.restaurante.service;

import br.com.fiap.restaurante.entities.Usuario;
import br.com.fiap.restaurante.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Camada de serviço, toda lógica de negócios deve ser inserida aqyi.
 */
@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    public Usuario salvar(Usuario usuario) {
        usuario.atualizarDataAlteracao();
        return repository.save(usuario);
    }
}
