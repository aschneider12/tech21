package br.com.fiap.restaurante.infra.service;

import br.com.fiap.restaurante.infra.database.entities.Item;
import br.com.fiap.restaurante.infra.database.entities.RestauranteEntity;
import br.com.fiap.restaurante.infra.database.repositories.jpa.ItemRepository;
import br.com.fiap.restaurante.infra.database.repositories.jpa.RestauranteRepository;
import br.com.fiap.restaurante.infra.dtos.ItemRequestDTO;
import br.com.fiap.restaurante.infra.dtos.ItemResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Deprecated
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final RestauranteRepository restauranteRepository;

    public ItemService(ItemRepository itemRepository, RestauranteRepository restauranteRepository) {
        this.itemRepository = itemRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public List<ItemResponseDTO> listarTodos() {
        return itemRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ItemResponseDTO buscarPorId(Long id) {
        return itemRepository.findById(id)
            .map(this::toDTO)
            .orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));
    }

    public ItemResponseDTO criar(ItemRequestDTO dto) {
        RestauranteEntity restaurante = restauranteRepository.findById(dto.restauranteId())
            .orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado"));

        Item item = new Item();
        item.setNome(dto.nome());
        item.setDescricao(dto.descricao());
        item.setPreco(dto.preco());
        item.setTipoVenda(dto.tipoVenda());
        item.setRestaurante(restaurante);
        item.setPathFoto(dto.pathFoto());

        itemRepository.save(item);
        return toDTO(item);
    }

    public ItemResponseDTO atualizar(Long id, ItemRequestDTO dto) {
        Item item = itemRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));

        item.setNome(dto.nome());
        item.setDescricao(dto.descricao());
        item.setPreco(dto.preco());
        item.setTipoVenda(dto.tipoVenda());
        item.setPathFoto(dto.pathFoto());

        itemRepository.save(item);
        return toDTO(item);
    }

    public void deletar(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new EntityNotFoundException("Item não encontrado");
        }
        itemRepository.deleteById(id);
    }

    private ItemResponseDTO toDTO(Item item) {
        return new ItemResponseDTO(
            item.getId(),
            item.getNome(),
            item.getDescricao(),
            item.getPreco(),
            item.getTipoVenda(),
            item.getRestaurante().getId(),
            item.getPathFoto()
        );
    }
}
