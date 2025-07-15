package br.com.fiap.restaurante.dtos;

import br.com.fiap.restaurante.entities.TipoUsuario;
import br.com.fiap.restaurante.entities.Usuario;

import java.util.List;

public record PerfilResponseDTO(String nome, List<TipoUsuario> perfis) {

}
