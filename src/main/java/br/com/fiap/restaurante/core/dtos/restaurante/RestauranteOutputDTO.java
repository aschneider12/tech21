package br.com.fiap.restaurante.core.dtos.restaurante;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.core.dtos.endereco.EnderecoDTO;
import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioOutputDTO;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioOutputDTO;

import java.util.List;

public record RestauranteOutputDTO(
        Long id,
        String nome,
        String tipoCozinha,
        String horarioFuncionamento,
        EnderecoDTO endereco,
        UsuarioOutputDTO dono,
        List<ItemCardapioOutputDTO> itensCardapio
){

    public static RestauranteOutputDTO create(
            Long id,
            String nome,
            String tipoCozinha,
            String horarioFuncionamento,
            EnderecoDTO endereco,
            UsuarioOutputDTO dono,
            List<ItemCardapioOutputDTO> itensCardapio

    ){

        return new RestauranteOutputDTO(id, nome, tipoCozinha, horarioFuncionamento,
                endereco, dono, itensCardapio);
    }
}
