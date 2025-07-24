package br.com.fiap.restaurante.core.dtos.endereco;

import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioOutputDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteOutputDTO;
import br.com.fiap.restaurante.core.dtos.usuario.UsuarioOutputDTO;

import java.util.List;

public record EnderecoDTO(

         Long id,
         String rua,
         String numero,
         String cidade,
         String estado,
         String cep
) {

    public static EnderecoDTO create(
            Long id,
            String rua,
            String numero,
            String cidade,
            String estado,
            String cep
    ){
        return new EnderecoDTO(id, rua, numero, cidade, estado, cep);
    }
}
