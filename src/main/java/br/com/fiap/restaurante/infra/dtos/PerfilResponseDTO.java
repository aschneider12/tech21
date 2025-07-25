package br.com.fiap.restaurante.infra.dtos;


import br.com.fiap.restaurante.infra.database.entities.TipoUsuario;

import java.util.List;

public record PerfilResponseDTO(String nome, List<TipoUsuario> perfis) {

}
