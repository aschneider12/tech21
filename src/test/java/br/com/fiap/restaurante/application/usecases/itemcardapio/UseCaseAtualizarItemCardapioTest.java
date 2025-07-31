package br.com.fiap.restaurante.application.usecases.itemcardapio;

import br.com.fiap.restaurante.application.exceptions.EntidadeJaExisteException;
import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.application.exceptions.ValidationException;
import br.com.fiap.restaurante.application.input.ItemCardapioInput;
import br.com.fiap.restaurante.application.output.ItemCardapioOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.restaurante.domain.models.ItemCardapio;
import br.com.fiap.restaurante.helper.Helper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UseCaseAtualizarItemCardapioTest {

    @Mock
    private IItemCardapioGateway gateway;

    private UseCaseAtualizarItemCardapio useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseAtualizarItemCardapio.create(gateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirAtualizarItemCardapio(){

        Long id = 1L;

        var itemCardapio = Helper.gerarItemCardapio();
        itemCardapio.getRestaurante().setId(99L);
        var itemCardapioInput = new ItemCardapioInput(
                itemCardapio.getNome(),
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                itemCardapio.getTipoVenda(),
                itemCardapio.getRestaurante().getId(),
                itemCardapio.getPathFoto()
        );
        itemCardapio.setId(id);



        when(gateway.buscarItemCardapioPorIdentificador(id)).thenReturn(itemCardapio);
        when(gateway.atualizar(any(ItemCardapio.class))).thenReturn(itemCardapio);

        ItemCardapioOutput itemCardapioAtualizado = useCase.run(id, itemCardapioInput);

        assertThat(itemCardapioAtualizado)
                .isNotNull()
                .isInstanceOf(ItemCardapioOutput.class);
        assertThat(itemCardapioAtualizado.nome()).isEqualTo(itemCardapio.getNome());
        assertThat(itemCardapioAtualizado.descricao()).isEqualTo(itemCardapio.getDescricao());
        assertThat(itemCardapioAtualizado.preco()).isEqualTo(itemCardapio.getPreco());
        assertThat(itemCardapioAtualizado.id()).isEqualTo(itemCardapio.getId());
        verify(gateway, times(1)).atualizar(any(ItemCardapio.class));
        verify(gateway, times(1)).buscarItemCardapioPorIdentificador(id);
    }

    @Test
    void deveFalharAoTentarAtualizarItemCardapioQueNaoExiste(){

        Long id = 1L;
        var itemCardapio = Helper.gerarItemCardapio();
        itemCardapio.getRestaurante().setId(99L);
        var itemCardapioInput = new ItemCardapioInput(
                itemCardapio.getNome(),
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                itemCardapio.getTipoVenda(),
                itemCardapio.getRestaurante().getId(),
                itemCardapio.getPathFoto()
        );
        itemCardapio.setId(id);

        when(gateway.buscarItemCardapioPorIdentificador(id)).thenReturn(null);

        assertThatThrownBy(() -> useCase.run(id, itemCardapioInput))
                .isInstanceOf(EntidadeNaoEncontradaException.class)
                .hasMessageContaining("Item Cardápio")
                .hasMessageContaining("ID - ")
                .hasMessageContaining(String.valueOf(id));
        verify(gateway, times(1)).buscarItemCardapioPorIdentificador(id);
        verify(gateway, never()).atualizar(any(ItemCardapio.class));
    }

    @Test
    void deveFalharAoTentarAtualizarItemCardapioDeUmRestauranteDiferente(){

        Long id = 1L;
        var itemCardapio = Helper.gerarItemCardapio();
        itemCardapio.getRestaurante().setId(99L);
        var itemCardapioInput = new ItemCardapioInput(
                itemCardapio.getNome(),
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                itemCardapio.getTipoVenda(),
                itemCardapio.getRestaurante().getId(),
                itemCardapio.getPathFoto()
        );
        itemCardapio.setId(id);

        var itemCardapioRestauranteDiferente = Helper.gerarItemCardapio();
        itemCardapioRestauranteDiferente.getRestaurante().setId(1000L);

        when(gateway.buscarItemCardapioPorIdentificador(id)).thenReturn(itemCardapioRestauranteDiferente);

        assertThatThrownBy(() -> useCase.run(id, itemCardapioInput))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Item não pertence ao restauranteId informado!");
        verify(gateway, times(1)).buscarItemCardapioPorIdentificador(id);
        verify(gateway, never()).atualizar(any(ItemCardapio.class));
    }


}
