package br.com.fiap.restaurante.application.gateways;

import br.com.fiap.restaurante.domain.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.restaurante.domain.interfaces.storage.IDataStorageItemCardapio;
import br.com.fiap.restaurante.domain.models.ItemCardapio;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemCardapioGateway implements IItemCardapioGateway {

    private final IDataStorageItemCardapio dataStorageItemCardapio;

    private ItemCardapioGateway(IDataStorageItemCardapio dataStorageItemCardapio) {
        this.dataStorageItemCardapio = dataStorageItemCardapio;
    }

    public static ItemCardapioGateway create(IDataStorageItemCardapio dataStorageItemCardapio) {
        return new ItemCardapioGateway(dataStorageItemCardapio);
    }

    @Override
    public ItemCardapio cadastrar(ItemCardapio itemCardapio) {

//        ItemCardapioInputDTO dto = ItemCardapioInputDTO.fromEntity(itemCardapio);

        var cadastrado = this.dataStorageItemCardapio.cadastrar(itemCardapio);

//        return ItemCardapioMapper.toDomain(dtpoCadastrado);

//        return itemCardapio.create(ic.nome(),ic.descricao(),ic.preco(),ic.tipoVenda());
        return cadastrado;
    }

    @Override
    public ItemCardapio atualizar(ItemCardapio itemCardapio) {

        return dataStorageItemCardapio.atualizar(itemCardapio);
    }

    @Override
    public boolean deletar(Long itemCardapioId) {

        return dataStorageItemCardapio.deletar(itemCardapioId);
    }

    @Override
    public List<ItemCardapio> buscarTodosItems(Long restauranteId) {

        return dataStorageItemCardapio.buscarTodosItensCardapio(restauranteId);

    }
//        List<ItemCardapioOutput> retornoDTOs = dataStorageItemCardapio.buscarTodosItensCardapio();
//        return ItemCardapioMapper.toDomain(retornoDTOs);
//        List<ItemCardapio> concreteItemCardapios = retornoDTOs.stream().map(dto
//                    -> ItemCardapio.create(dto.id(),dto.nome(), dto.descricao(), dto.preco(), dto.tipoVenda()))
//                    .collect(Collectors.toList());
//        return concreteItemCardapios;


    @Override
    public ItemCardapio buscarItemCardapioPorNome(String nomeItem) {

        var retorno = dataStorageItemCardapio.buscarItemCardapioPorNome(nomeItem);

        return retorno;
    }

    @Override
    public ItemCardapio buscarItemCardapioPorIdentificador(Long id) {

//      ItemCardapioOutput retornoDTO = dataStorageItemCardapio.buscarItemCardapioPorIdentificador(id);
//      return ItemCardapioMapper.toDomain(retornoDTO);
//      return ItemCardapio.create(retornoDTO.id() ,retornoDTO.nome(), retornoDTO.descricao(), retornoDTO.preco(), retornoDTO.tipoVenda());

        return dataStorageItemCardapio.buscarItemCardapioPorIdentificador(id);
    }

}

