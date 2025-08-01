package br.com.fiap.restaurante.infra.database.repositories.adapter;

import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.domain.interfaces.storage.IDataStorageItemCardapio;
import br.com.fiap.restaurante.domain.models.ItemCardapio;
import br.com.fiap.restaurante.infra.database.entities.ItemCardapioEntity;
import br.com.fiap.restaurante.infra.database.mappers.ItemCardapioEntityMapper;
import br.com.fiap.restaurante.infra.database.repositories.jpa.ItemRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ItemCardapioRepositoryAdapter implements IDataStorageItemCardapio {

    private final ItemRepository repository;

    public ItemCardapioRepositoryAdapter(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public ItemCardapio cadastrar(ItemCardapio itemCardapio) {
        ItemCardapioEntity entity = ItemCardapioEntityMapper.INSTANCE.toEntity(itemCardapio);

        entity = repository.save(entity);

        return ItemCardapioEntityMapper.INSTANCE.toDomain(entity);
    }

    @Override
    public ItemCardapio atualizar(ItemCardapio itemCardapio) {

        ItemCardapioEntity entity = ItemCardapioEntityMapper.INSTANCE.toEntity(itemCardapio);

        entity = repository.save(entity);

        return ItemCardapioEntityMapper.INSTANCE.toDomain(entity);
    }

    @Override
    public boolean deletar(Long itemCardapioId) {

        if(repository.existsById(itemCardapioId))
            repository.deleteById(itemCardapioId);
        else
            throw new EntidadeNaoEncontradaException("Item Cardápio", "ID - "+itemCardapioId);

        return true;//FALSE ou EXCEPTION?
    }

    @Override
    public List<ItemCardapio> buscarTodosItensCardapio(Long restauranteId) {
        List<ItemCardapioEntity> cardapio = repository.findAllByRestauranteId(restauranteId);

        return ItemCardapioEntityMapper.INSTANCE.toDomain(cardapio);
    }

    @Override
    public ItemCardapio buscarItemCardapioPorIdentificador(Long itemCardapioId) {
        ItemCardapioEntity itemCardapioEntityBD = repository.findById(itemCardapioId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Item Cardápio", "ID: "+itemCardapioId));

        return ItemCardapioEntityMapper.INSTANCE.toDomain(itemCardapioEntityBD);
    }

    @Override
    public ItemCardapio buscarItemCardapioPorNome(String nome) {

        Optional<ItemCardapioEntity> entityBD = repository.findByNome(nome);

        return entityBD.map(ItemCardapioEntityMapper.INSTANCE::toDomain).orElse(null);
    }
}