package br.com.fiap.restaurante.application.usecases.itemcardapio;

import br.com.fiap.restaurante.domain.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.restaurante.domain.models.ItemCardapio;
import br.com.fiap.restaurante.domain.models.Restaurante;
import br.com.fiap.restaurante.helper.Helper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UseCaseBuscarTodosItemCardapioTest {
    @Mock
    private IItemCardapioGateway gateway;

    private UseCaseBuscarTodosItemCardapio useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseBuscarTodosItemCardapio.create(gateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirBuscarTodosOsItensCardapioPorRestaurante(){
        Long idRestaurante = 1L;
        var itemCardapio1 =  Helper.gerarItemCardapio();
        var itemCardapio2 =  Helper.gerarItemCardapio();
        var itemCardapio3 =  Helper.gerarItemCardapio();
        itemCardapio1.setId(1L);
        itemCardapio2.setId(2L);
        itemCardapio3.setId(3L);
        itemCardapio1.getRestaurante().setId(idRestaurante);
        itemCardapio2.getRestaurante().setId(idRestaurante);
        itemCardapio3.getRestaurante().setId(idRestaurante);
        List<ItemCardapio> itensCardapio = List.of(
                itemCardapio1,
                itemCardapio2,
                itemCardapio3
        );

        when(gateway.buscarTodosItems(idRestaurante)).thenReturn(itensCardapio);

        var todosItensCardapio = useCase.run(idRestaurante);

        assertThat(todosItensCardapio)
                .isNotNull()
                .hasSize(itensCardapio.size());


        for (int i = 0; i < itensCardapio.size(); i++) {
            assertThat(todosItensCardapio.get(i).nome())
                    .isEqualTo(itensCardapio.get(i).getNome());

            assertThat(todosItensCardapio.get(i).descricao())
                    .isEqualTo(itensCardapio.get(i).getDescricao());


            assertThat(todosItensCardapio.get(i).descricao())
                    .isEqualTo(itensCardapio.get(i).getDescricao());
        }

        verify(gateway, times(1)).buscarTodosItems(idRestaurante);
    }

}
