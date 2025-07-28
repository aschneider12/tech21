package br.com.fiap.restaurante.infra.service;

import br.com.fiap.restaurante.infra.dtos.PerfilResponseDTO;
import br.com.fiap.restaurante.infra.database.entities.TipoUsuario;
import br.com.fiap.restaurante.infra.database.entities.UsuarioEntity;
import br.com.fiap.restaurante.infra.database.entities.UsuarioPerfilEntity;
import br.com.fiap.restaurante.application.exceptions.ValidationException;
import br.com.fiap.restaurante.infra.database.repositories.jpa.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Deprecated
@Service
public class PerfilService {


    @Autowired
    private UsuarioRepository repository;

    public PerfilResponseDTO listarPerfis(Long usuarioId) {

        List<TipoUsuario> listaDePerfis = null;

        UsuarioEntity usuarioEntity = repository.findById(usuarioId)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado - ID: "+usuarioId));

        Set<UsuarioPerfilEntity> perfis = usuarioEntity.getPerfis();

        if(perfis != null)
            listaDePerfis = perfis.stream().map(UsuarioPerfilEntity::getTipoUsuario).toList();

        return new PerfilResponseDTO(usuarioEntity.getNome(), listaDePerfis);
    }

    public void adicionarPerfil(Long usuarioId, List<TipoUsuario> perfisAdd) {

        UsuarioEntity usuarioEntity = repository.findById(usuarioId)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado - ID: "+usuarioId));

        if(usuarioEntity.getPerfis() == null)
            usuarioEntity.setPerfis(new HashSet<>());

        usuarioEntity.getPerfis().addAll(
                perfisAdd.stream()
                        .map(tipoUsuario -> {
                            UsuarioPerfilEntity add = new UsuarioPerfilEntity(usuarioEntity, tipoUsuario);
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
