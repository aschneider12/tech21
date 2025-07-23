package br.com.fiap.restaurante.application.repositories.adapter;

import br.com.fiap.restaurante.application.repositories.jpa.ItemRepository;
import br.com.fiap.restaurante.core.domain.entities.ItemCardapio;
import br.com.fiap.restaurante.core.interfaces.gateway.IItemCardapioGateway;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemCardapioRepositoryAdapter implements IItemCardapioGateway {

    private final ItemRepository repository;

    public ItemCardapioRepositoryAdapter(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public ItemCardapio cadastrar(ItemCardapio item) {
        return repository.save(item);
    }

    @Override
    public List<ItemCardapio> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public ItemCardapio buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ItemCardapio buscarPorNome(String nome) {
        return repository.findByNome(nome).orElse(null);
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}

