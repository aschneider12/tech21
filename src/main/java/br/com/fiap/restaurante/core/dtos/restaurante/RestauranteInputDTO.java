package br.com.fiap.restaurante.core.dtos.restaurante;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.core.dtos.endereco.EnderecoDTO;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioInputDTO;
import java.util.List;

public record RestauranteInputDTO(
        String nome,
        String tipoCozinha,
        String horarioFuncionamento,
        EnderecoDTO endereco,
        UsuarioInputDTO dono,
        List<ItemCardapio> itensCardapio) {

}

