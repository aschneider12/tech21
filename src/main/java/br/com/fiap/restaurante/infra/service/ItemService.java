package br.com.fiap.restaurante.infra.service;

import br.com.fiap.restaurante.infra.database.entities.ItemCardapioEntity;
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

        ItemCardapioEntity itemCardapioEntity = new ItemCardapioEntity();
        itemCardapioEntity.setNome(dto.nome());
        itemCardapioEntity.setDescricao(dto.descricao());
        itemCardapioEntity.setPreco(dto.preco());
        itemCardapioEntity.setTipoVenda(dto.tipoVenda());
        itemCardapioEntity.setRestaurante(restaurante);
        itemCardapioEntity.setPathFoto(dto.pathFoto());

        itemRepository.save(itemCardapioEntity);
        return toDTO(itemCardapioEntity);
    }

    public ItemResponseDTO atualizar(Long id, ItemRequestDTO dto) {
        ItemCardapioEntity itemCardapioEntity = itemRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));

        itemCardapioEntity.setNome(dto.nome());
        itemCardapioEntity.setDescricao(dto.descricao());
        itemCardapioEntity.setPreco(dto.preco());
        itemCardapioEntity.setTipoVenda(dto.tipoVenda());
        itemCardapioEntity.setPathFoto(dto.pathFoto());

        itemRepository.save(itemCardapioEntity);
        return toDTO(itemCardapioEntity);
    }

//    public void deletar(Long id) {
//        if (!itemRepository.existsById(id)) {
//            throw new EntityNotFoundException("Item não encontrado");
//        }
//        itemRepository.deleteById(id);
//    }

    private ItemResponseDTO toDTO(ItemCardapioEntity itemCardapioEntity) {
        return new ItemResponseDTO(
            itemCardapioEntity.getId(),
            itemCardapioEntity.getNome(),
            itemCardapioEntity.getDescricao(),
            itemCardapioEntity.getPreco(),
            itemCardapioEntity.getTipoVenda(),
            itemCardapioEntity.getRestaurante().getId(),
            itemCardapioEntity.getPathFoto()
        );
    }
}
