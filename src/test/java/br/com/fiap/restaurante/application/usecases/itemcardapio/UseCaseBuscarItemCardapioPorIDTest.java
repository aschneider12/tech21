package br.com.fiap.restaurante.application.usecases.itemcardapio;

import br.com.fiap.restaurante.application.output.ItemCardapioOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.restaurante.helper.Helper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UseCaseBuscarItemCardapioPorIDTest {

    @Mock
    private IItemCardapioGateway gateway;

    private UseCaseBuscarItemCardapioPorID useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseBuscarItemCardapioPorID.create(gateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirBuscarItemCardapioPorId(){
        Long idItemCardapio = 1L;
        Long idRestaurante = 99L;
        var itemCardapio = Helper.gerarItemCardapio();
        itemCardapio.setId(idItemCardapio);
        itemCardapio.getRestaurante().setId(idRestaurante);

        when(gateway.buscarItemCardapioPorIdentificador(idItemCardapio)).thenReturn(itemCardapio);

        ItemCardapioOutput itemRetornado = useCase.run(idRestaurante, idItemCardapio);

        assertThat(itemRetornado)
                .isNotNull();
        assertThat(itemRetornado.nome())
                .isEqualTo(itemCardapio.getNome());
        assertThat(itemRetornado.id())
                .isEqualTo(idItemCardapio);
        assertThat(itemRetornado.restauranteOutput().id())
                .isEqualTo(idRestaurante);
        verify(gateway, times(1)).buscarItemCardapioPorIdentificador(idItemCardapio);

    }
}
