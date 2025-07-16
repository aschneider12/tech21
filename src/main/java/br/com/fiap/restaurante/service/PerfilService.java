package br.com.fiap.restaurante.service;

import br.com.fiap.restaurante.dtos.PerfilRequestDTO;
import br.com.fiap.restaurante.dtos.PerfilResponseDTO;
import br.com.fiap.restaurante.entities.TipoUsuario;
import br.com.fiap.restaurante.entities.Usuario;
import br.com.fiap.restaurante.entities.UsuarioPerfil;
import br.com.fiap.restaurante.exceptions.ValidationException;
import br.com.fiap.restaurante.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerfilService {


    @Autowired
    private UsuarioRepository repository;

    public PerfilResponseDTO listarPerfis(Long usuarioId) {

        List<TipoUsuario> listaDePerfis = null;

        Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado - ID: "+usuarioId));

        List<UsuarioPerfil> perfis = usuario.getPerfis();

        if(perfis != null)
            listaDePerfis = perfis.stream().map(UsuarioPerfil::getTipoUsuario).toList();

        return new PerfilResponseDTO(usuario.getNome(), listaDePerfis);
    }

    public void adicionarPerfil(Long usuarioId, List<TipoUsuario> perfisAdd) {

        Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado - ID: "+usuarioId));

        if(usuario.getPerfis() == null)
            usuario.setPerfis(new ArrayList<>());

        usuario.getPerfis().addAll(
                perfisAdd.stream()
                        .map(tipoUsuario -> {
                            UsuarioPerfil add = new UsuarioPerfil(usuario, tipoUsuario);
                            if(!usuario.getPerfis().contains(add))
                                return add;
                            else return null;
                        }).toList()
        );

        repository.save(usuario);
    }

    public void removerPerfil(Long usuarioId, List<TipoUsuario> perfisDel) {
        Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado - ID: "+usuarioId));

        if(usuario.getPerfis() != null) {

            usuario.getPerfis().removeIf(perfil -> perfisDel.contains(perfil.getTipoUsuario()));
            repository.save(usuario);
        }
    }
}
