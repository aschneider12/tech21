package br.com.fiap.restaurante.application.mappers;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioInputDTO;
import br.com.fiap.restaurante.core.dtos.itemcardapio.ItemCardapioOutputDTO;

public class ItemCardapioMapper {

    public static ItemCardapio toEntity(ItemCardapioInputDTO dto) {
        return ItemCardapio.create(
            dto.nome(),
            dto.descricao(),
            dto.preco(),
            dto.tipoVenda(),
            dto.pathFoto()
        );
    }

    public static ItemCardapioOutputDTO toDTO(ItemCardapio entity) {
        return new ItemCardapioOutputDTO(
            entity.getId(),
            entity.getNome(),
            entity.getDescricao(),
            entity.getPreco(),
            entity.getTipoVenda(),
            entity.getPathFoto()
        );
    }
}
