package br.com.fiap.restaurante.service;

import br.com.fiap.restaurante.entities.Usuario;
import br.com.fiap.restaurante.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Camada de serviço, toda lógica de negócios deve ser inserida aqyi.
 */
@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    public Usuario salvar(Usuario usuario) {

        //validações necessárias

        //senha forte?
        //login ja existe?
        //senha igual anterior?

        usuario.setDataUltimaAlteracao(LocalDate.now());

        return repository.save(usuario);
    }
    public void deletar(Long id) {
        if(id != null)
          repository.deleteById(id);
    }

    public List<Usuario> buscarTodosUsuarios() {
        return repository.findAll();
    }

}
