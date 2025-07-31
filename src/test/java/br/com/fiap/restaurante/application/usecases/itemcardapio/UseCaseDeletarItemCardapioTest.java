package br.com.fiap.restaurante.application.usecases.itemcardapio;

import br.com.fiap.restaurante.application.exceptions.ValidationException;
import br.com.fiap.restaurante.domain.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.restaurante.helper.Helper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class UseCaseDeletarItemCardapioTest {

    @Mock
    private IItemCardapioGateway gateway;

    private UseCaseDeletarItemCardapio useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseDeletarItemCardapio.create(gateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirDeletarItemCardapio(){

        Long idItemCardapio = 1L;
        Long idRestaurante = 99L;

        var itemCardapio = Helper.gerarItemCardapio();
        itemCardapio.getRestaurante().setId(idRestaurante);
        itemCardapio.setId(idItemCardapio);


        when(gateway.buscarItemCardapioPorIdentificador(idItemCardapio)).thenReturn(itemCardapio);
        when(gateway.deletar(idItemCardapio)).thenReturn(true);

        boolean itemFoiDeletado = useCase.run(idRestaurante, idItemCardapio);

        assertThat(itemFoiDeletado)
                .isTrue();
        verify(gateway, times(1)).deletar(idItemCardapio);
        verify(gateway, times(1)).buscarItemCardapioPorIdentificador(idItemCardapio);
    }

    @Test
    void deveFalharAoTentarDeletarItemCardapioDeRestauranteDiferenteDoInformado(){

        Long idItemCardapio = 1L;
        Long idRestaurante = 99L;

        var itemCardapio = Helper.gerarItemCardapio();
        itemCardapio.getRestaurante().setId(idRestaurante);
        itemCardapio.setId(idItemCardapio);


        when(gateway.buscarItemCardapioPorIdentificador(idItemCardapio)).thenReturn(itemCardapio);

        assertThatThrownBy(() -> useCase.run(100L, idItemCardapio))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Item não pertence ao restauranteId informado.");
        verify(gateway, times(1)).buscarItemCardapioPorIdentificador(idItemCardapio);
        verify(gateway, never()).deletar(idItemCardapio);
    }


}
