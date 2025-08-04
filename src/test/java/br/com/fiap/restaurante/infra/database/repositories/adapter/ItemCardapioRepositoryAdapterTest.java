package br.com.fiap.restaurante.infra.database.repositories.adapter;

import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.domain.models.ItemCardapio;
import br.com.fiap.restaurante.domain.models.Restaurante;
import br.com.fiap.restaurante.helper.Helper;
import br.com.fiap.restaurante.infra.database.entities.ItemCardapioEntity;
import br.com.fiap.restaurante.infra.database.entities.RestauranteEntity;
import br.com.fiap.restaurante.infra.database.mappers.ItemCardapioEntityMapper;
import br.com.fiap.restaurante.infra.database.mappers.RestauranteEntityMapper;
import br.com.fiap.restaurante.infra.database.repositories.jpa.ItemRepository;
import br.com.fiap.restaurante.infra.database.repositories.jpa.RestauranteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ItemCardapioRepositoryAdapterTest {

    @Mock
    private ItemRepository repository;

    private ItemCardapioRepositoryAdapter adapter;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        adapter = new ItemCardapioRepositoryAdapter(repository);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirCadastrarItemCardapio(){
        Long id = 1L;
        var itemCardapio = Helper.gerarItemCardapio();
        itemCardapio.setId(id);
        var itemCardapioEntity = ItemCardapioEntityMapper.INSTANCE.toEntity(itemCardapio);

        when(repository.save(any(ItemCardapioEntity.class))).thenReturn(itemCardapioEntity);

        var itemCardapioCadastrado = adapter.cadastrar(itemCardapio);

        assertThat(itemCardapioCadastrado)
                .isNotNull()
                .isInstanceOf(ItemCardapio.class)
                .isEqualTo(itemCardapio);

        verify(repository, times(1)).save(any(ItemCardapioEntity.class));

    }

        @Test
        void devePermitirAtualizarItemCardapio() {
            var id = 1L;
            var itemCardapio = Helper.gerarItemCardapio();
            itemCardapio.setId(id);
            var itemCardapioEntity = ItemCardapioEntityMapper.INSTANCE.toEntity(itemCardapio);

            when(repository.save(any(ItemCardapioEntity.class))).thenReturn(itemCardapioEntity);

            var itemCardapioAtualizado = adapter.atualizar(itemCardapio);

            assertThat(itemCardapioAtualizado)
                    .isNotNull()
                    .isInstanceOf(ItemCardapio.class)
                    .isEqualTo(itemCardapio);

            verify(repository, times(1)).save(any(ItemCardapioEntity.class));
        }



    @Nested
    class DeletarItemCardapio{
        @Test
        void devePermitirDeletarItemCardapio(){
            var id = 1L;

            when(repository.existsById(id)).thenReturn(true);
            doNothing().when(repository).deleteById(id);

            boolean restauranteFoiDeletado = adapter.deletar( id);

            assertThat(restauranteFoiDeletado).isTrue();
            verify(repository, times(1)).deleteById(id);
            verify(repository, times(1)).existsById(id);

        }

        @Test
        void deveFalharAoTentarDeletarItemCardapioQueNaoExiste(){
            var id = 1L;

            when(repository.existsById(id)).thenReturn(false);

            assertThatThrownBy(() -> adapter.deletar(id))
                    .isInstanceOf(EntidadeNaoEncontradaException.class)
                    .hasMessageContaining("Item Cardápio")
                    .hasMessageContaining("ID - ")
                    .hasMessageContaining(String.valueOf(id));
            verify(repository, never()).deleteById(anyLong());
            verify(repository, times(1)).existsById(id);
        }

    }

    @Test
    void devePermitirRetornarTodosOsItensCardapio(){
        Long idRestaurante = 99L;
        ItemCardapioEntity itemCardapioEntity1 =  ItemCardapioEntityMapper.INSTANCE.toEntity(Helper.gerarItemCardapio());
        ItemCardapioEntity itemCardapioEntity2 =  ItemCardapioEntityMapper.INSTANCE.toEntity(Helper.gerarItemCardapio());
        ItemCardapioEntity itemCardapioEntity3 =  ItemCardapioEntityMapper.INSTANCE.toEntity(Helper.gerarItemCardapio());
        itemCardapioEntity1.setId(1L);
        itemCardapioEntity2.setId(2L);
        itemCardapioEntity3.setId(3L);

        itemCardapioEntity1.getRestaurante().setId(idRestaurante);
        itemCardapioEntity2.getRestaurante().setId(idRestaurante);
        itemCardapioEntity3.getRestaurante().setId(idRestaurante);

        List<ItemCardapioEntity> itensCardapioEntity = List.of(
            itemCardapioEntity1,
            itemCardapioEntity2,
            itemCardapioEntity3
        );

        when(repository.findAllByRestauranteId(any(Long.class))).thenReturn(itensCardapioEntity);

        List<ItemCardapio> itensCardapiosASeremRetornados = adapter.buscarTodosItensCardapio(idRestaurante);

        assertThat(itensCardapiosASeremRetornados)
                .isNotNull()
                .hasSize(itensCardapioEntity.size());


        for (int i = 0; i < itensCardapioEntity.size(); i++) {
            assertThat(itensCardapiosASeremRetornados.get(i).getNome())
                    .isEqualTo(itensCardapioEntity.get(i).getNome());

            assertThat(itensCardapiosASeremRetornados.get(i).getDescricao())
                    .isEqualTo(itensCardapioEntity.get(i).getDescricao());

            assertThat(itensCardapiosASeremRetornados.get(i).getId())
                    .isEqualTo(itensCardapioEntity.get(i).getId());
        }

        verify(repository, times(1)).findAllByRestauranteId(any(Long.class));

    }

    @Nested
    class ConsultarItemCardapioPorId{
        @Test
        void devePermitirConsultarItemCardapioPorId(){
            var id = 1L;
            var itemCardapio = Helper.gerarItemCardapio();
            itemCardapio.setId(id);
            var itemCardapioEntity = ItemCardapioEntityMapper.INSTANCE.toEntity(itemCardapio);

            when(repository.findById(any(Long.class))).thenReturn(Optional.of(itemCardapioEntity));

            var itemCardapioObtido = adapter.buscarItemCardapioPorIdentificador(id);

            assertThat(itemCardapioObtido)
                    .isNotNull()
                    .isEqualTo(itemCardapio);
            verify(repository, times(1)).findById(id);

        }

        @Test
        void deveFalharAoTentarEncontrarItemCardapioQueNaoExiste(){
            Long id = 1L;
            when(repository.findById(any(Long.class))).thenReturn(Optional.empty());


            assertThatThrownBy(() -> adapter.buscarItemCardapioPorIdentificador(id))
                    .isInstanceOf(EntidadeNaoEncontradaException.class)
                    .hasMessageContaining("Item Cardápio")
                    .hasMessageContaining("ID: ")
                    .hasMessageContaining(id.toString());

            verify(repository, times(1)).findById(id);


        }
    }


    @Nested
    class ConsultarItemCardapioPorNome{
        @Test
        void devePermitirConsultarItemCardapioPorNome(){
            var id = 1L;
            var nome = "item teste";
            var itemCardapio = Helper.gerarItemCardapio();
            itemCardapio.setId(id);
            itemCardapio.setNome(nome);

            var itemCardapioEntity = ItemCardapioEntityMapper.INSTANCE.toEntity(itemCardapio);

            when(repository.findByNome(any(String.class))).thenReturn(Optional.of(itemCardapioEntity));

            var itemCardapioObtido = adapter.buscarItemCardapioPorNome(nome);

            assertThat(itemCardapioObtido)
                    .isNotNull()
                    .isEqualTo(itemCardapio);
            verify(repository, times(1)).findByNome(nome);
        }

//        @Test
//        void deveRetornarNuloCasoNaoEncontreRestauranteNome(){
//            String nome = "Restaurante teste";
//            when(restauranteRepository.findByNome(any(String.class))).thenReturn(Optional.empty());
//
//            var restauranteObtido = adapter.buscarRestaurantePorNome(nome);
//
//            assertThat(restauranteObtido).isNull();
//            verify(restauranteRepository, times(1)).findByNome(nome);
//
//        }
    }
}
