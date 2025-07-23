package br.com.fiap.restaurante.core.interfaces.gateway;

import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;

import java.util.List;

public interface IItemCardapioGateway {
    ItemCardapio cadastrar(ItemCardapio item);
    List<ItemCardapio> buscarTodos();
    ItemCardapio buscarPorId(Long id);
    ItemCardapio buscarPorNome(String nome);
    void deletar(Long id);
}
