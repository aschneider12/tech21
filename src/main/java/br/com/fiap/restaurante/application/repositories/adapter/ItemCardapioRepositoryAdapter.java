package br.com.fiap.restaurante.application.repositories.adapter;

import br.com.fiap.restaurante.application.entities.ItemCardapioEntity;
import br.com.fiap.restaurante.application.repositories.jpa.ItemCardapioRepository;
import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.core.interfaces.gateway.IItemCardapioGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemCardapioRepositoryAdapter implements IItemCardapioGateway {

    private final ItemCardapioRepository repository;

    public ItemCardapioRepositoryAdapter(ItemCardapioRepository repository) {
        this.repository = repository;
    }

    @Override
    public ItemCardapio cadastrar(ItemCardapio item) {
        ItemCardapioEntity entity = new ItemCardapioEntity(
            item.getId(), item.getNome(), item.getDescricao(),
            item.getPreco(), item.getTipoVenda(), item.getPathFoto()
        );

        ItemCardapioEntity saved = repository.save(entity);

        return ItemCardapio.create(
            saved.getId(), saved.getNome(), saved.getDescricao(),
            saved.getPreco(), saved.getTipoVenda(), saved.getPathFoto()
        );
    }

    @Override
    public List<ItemCardapio> buscarTodos() {
        return repository.findAll()
            .stream()
            .map(e -> ItemCardapio.create(
                e.getId(), e.getNome(), e.getDescricao(), e.getPreco(), e.getTipoVenda(), e.getPathFoto()
            ))
            .collect(Collectors.toList());
    }

    @Override
    public ItemCardapio buscarPorId(Long id) {
        return repository.findById(id)
            .map(e -> ItemCardapio.create(e.getId(), e.getNome(), e.getDescricao(), e.getPreco(), e.getTipoVenda(), e.getPathFoto()))
            .orElse(null);
    }

    @Override
    public ItemCardapio buscarPorNome(String nome) {
        return repository.findByNome(nome)
            .map(e -> ItemCardapio.create(e.getId(), e.getNome(), e.getDescricao(), e.getPreco(), e.getTipoVenda(), e.getPathFoto()))
            .orElse(null);
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}

