package br.com.fiap.restaurante.application.dtos;

import br.com.fiap.restaurante.application.entities.TipoUsuario;

import java.util.List;

public record PerfilResponseDTO(String nome, List<TipoUsuario> perfis) {

}
