package br.com.fiap.restaurante.application.usecases.itemcardapio;

import br.com.fiap.restaurante.application.exceptions.EntidadeJaExisteException;
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

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

public class UseCaseCadastrarItemCardapioTest {
    @Mock
    private IItemCardapioGateway gateway;

    private UseCaseCadastrarItemCardapio useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseCadastrarItemCardapio.create(gateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirCadastrarItemCardapio(){

        String nome = "item teste";

        var itemCardapio = Helper.gerarItemCardapio();
        itemCardapio.getRestaurante().setId(99L);
        var itemCardapioInput = new ItemCardapioInput(
                nome,
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                itemCardapio.getTipoVenda(),
                itemCardapio.getRestaurante().getId(),
                itemCardapio.getPathFoto()
        );
        itemCardapio.setId(1L);

        ItemCardapio itemCardapioASerCadastrado = ItemCardapioInput.toDomain(itemCardapioInput);


        when(gateway.buscarItemCardapioPorNome(nome)).thenReturn(null);
        when(gateway.cadastrar(itemCardapioASerCadastrado)).thenReturn(itemCardapio);

        ItemCardapioOutput itemCardapioCadastrado = useCase.run(itemCardapioInput);

        assertThat(itemCardapioCadastrado)
                .isNotNull()
                .isInstanceOf(ItemCardapioOutput.class);
        assertThat(itemCardapioCadastrado.nome()).isEqualTo(itemCardapio.getNome());
        assertThat(itemCardapioCadastrado.descricao()).isEqualTo(itemCardapio.getDescricao());
        assertThat(itemCardapioCadastrado.preco()).isEqualTo(itemCardapio.getPreco());
        assertThat(itemCardapioCadastrado.id()).isEqualTo(itemCardapio.getId());
        verify(gateway, times(1)).cadastrar(itemCardapioASerCadastrado);
        verify(gateway, times(1)).buscarItemCardapioPorNome(nome);
    }

    @Test
    void deveFalharAoCadastrarItemCardapioQueJaExiste(){

        String nome = "item teste";

        var itemCardapio = Helper.gerarItemCardapio();
        itemCardapio.getRestaurante().setId(99L);
        var itemCardapioInput = new ItemCardapioInput(
                nome,
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                itemCardapio.getTipoVenda(),
                itemCardapio.getRestaurante().getId(),
                itemCardapio.getPathFoto()
        );
        itemCardapio.setId(1L);

        when(gateway.buscarItemCardapioPorNome(nome)).thenReturn(itemCardapio);

        assertThatThrownBy(() -> useCase.run(itemCardapioInput))
                .isInstanceOf(EntidadeJaExisteException.class)
                .hasMessageContaining("Item Cardápio")
                .hasMessageContaining(nome);
        verify(gateway, times(1)).buscarItemCardapioPorNome(nome);
        verify(gateway, never()).cadastrar(any(ItemCardapio.class));
    }


    @Test
    void deveFalharAoCadastrarItemCardapioComDadosInvalidos(){

        String nome = "item teste";

        var itemCardapio = Helper.gerarItemCardapio();
        itemCardapio.setPreco(BigDecimal.valueOf(-1.0));
        itemCardapio.getRestaurante().setId(99L);
        var itemCardapioInput = new ItemCardapioInput(
                nome,
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                itemCardapio.getTipoVenda(),
                itemCardapio.getRestaurante().getId(),
                itemCardapio.getPathFoto()
        );
        itemCardapio.setId(1L);

        when(gateway.buscarItemCardapioPorNome(nome)).thenReturn(null);

        assertThatThrownBy(() -> useCase.run(itemCardapioInput))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Preço do produto não pode ser menor que zero!");
        verify(gateway, times(1)).buscarItemCardapioPorNome(nome);
        verify(gateway, never()).cadastrar(any(ItemCardapio.class));
    }

}
