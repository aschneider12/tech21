package br.com.fiap.restaurante.infra.service;

import br.com.fiap.restaurante.infra.dtos.PerfilResponseDTO;
import br.com.fiap.restaurante.infra.database.entities.TipoUsuario;
import br.com.fiap.restaurante.infra.database.entities.UsuarioEntity;
import br.com.fiap.restaurante.infra.database.entities.UsuarioPerfil;
import br.com.fiap.restaurante.domain.exceptions.ValidationException;
import br.com.fiap.restaurante.infra.database.repositories.jpa.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Deprecated
@Service
public class PerfilService {


    @Autowired
    private UsuarioRepository repository;

    public PerfilResponseDTO listarPerfis(Long usuarioId) {

        List<TipoUsuario> listaDePerfis = null;

        UsuarioEntity usuarioEntity = repository.findById(usuarioId)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado - ID: "+usuarioId));

        List<UsuarioPerfil> perfis = usuarioEntity.getPerfis();

        if(perfis != null)
            listaDePerfis = perfis.stream().map(UsuarioPerfil::getTipoUsuario).toList();

        return new PerfilResponseDTO(usuarioEntity.getNome(), listaDePerfis);
    }

    public void adicionarPerfil(Long usuarioId, List<TipoUsuario> perfisAdd) {

        UsuarioEntity usuarioEntity = repository.findById(usuarioId)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado - ID: "+usuarioId));

        if(usuarioEntity.getPerfis() == null)
            usuarioEntity.setPerfis(new ArrayList<>());

        usuarioEntity.getPerfis().addAll(
                perfisAdd.stream()
                        .map(tipoUsuario -> {
                            UsuarioPerfil add = new UsuarioPerfil(usuarioEntity, tipoUsuario);
                            if(!usuarioEntity.getPerfis().contains(add))
                                return add;
                            else return null;
                        }).toList()
        );

        repository.save(usuarioEntity);
    }

    public void removerPerfil(Long usuarioId, List<TipoUsuario> perfisDel) {
        UsuarioEntity usuarioEntity = repository.findById(usuarioId)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado - ID: "+usuarioId));

        if(usuarioEntity.getPerfis() != null) {

            usuarioEntity.getPerfis().removeIf(perfil -> perfisDel.contains(perfil.getTipoUsuario()));
            repository.save(usuarioEntity);
        }
    }
}
