package br.com.fiap.restaurante.infra.dtos;

import br.com.fiap.restaurante.infra.database.entities.TipoUsuario;

import java.util.List;

public record PerfilRequestDTO(List<TipoUsuario> perfis) {

}
