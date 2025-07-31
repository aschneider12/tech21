package br.com.fiap.restaurante.application.gateways;

import br.com.fiap.restaurante.domain.interfaces.storage.IDataStorageItemCardapio;
import br.com.fiap.restaurante.domain.models.ItemCardapio;
import br.com.fiap.restaurante.helper.Helper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ItemCardapioGatewayTest {

    @Mock
    private IDataStorageItemCardapio dataSource;

    private ItemCardapioGateway gateway;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        gateway = ItemCardapioGateway.create(dataSource);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirCadastrarItemCardapio(){
        Long idItemCardapio = 1L;
        var itemCardapio = Helper.gerarItemCardapio();
        itemCardapio.setId(idItemCardapio);

        when(dataSource.cadastrar(itemCardapio)).thenReturn(itemCardapio);


        var itemCardapioCadastrado = gateway.cadastrar(itemCardapio);


        assertThat(itemCardapioCadastrado)
                .isNotNull()
                .isInstanceOf(ItemCardapio.class)
                .isEqualTo(itemCardapio);

        verify(dataSource, times(1)).cadastrar(itemCardapio);

    }


    @Test
    void devePermitirAtualizarItemCardapio(){
        Long idItemCardapio = 1L;
        var itemCardapio = Helper.gerarItemCardapio();
        itemCardapio.setId(idItemCardapio);

        when(dataSource.atualizar(itemCardapio)).thenReturn(itemCardapio);


        var itemCardapioAtualizado = gateway.atualizar(itemCardapio);


        assertThat(itemCardapioAtualizado)
                .isNotNull()
                .isInstanceOf(ItemCardapio.class)
                .isEqualTo(itemCardapio);

        verify(dataSource, times(1)).atualizar(itemCardapio);

    }

    @Test
    void devePermitirDeletarItemCardapio(){
        var id = 1L;

        when(dataSource.deletar(id)).thenReturn(true);

        boolean itemCardapioFoiDeleteado = gateway.deletar( id);

        assertThat(itemCardapioFoiDeleteado).isTrue();
        verify(dataSource, times(1)).deletar(id);

    }



    @Test
    void devePermitirRetornarTodosOsItensDoCardapio(){

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

        when(dataSource.buscarTodosItensCardapio(idRestaurante)).thenReturn(itensCardapio);

        var todosItensCardapio = gateway.buscarTodosItems(idRestaurante);

        assertThat(todosItensCardapio)
                .isNotNull()
                .hasSize(itensCardapio.size());


        for (int i = 0; i < itensCardapio.size(); i++) {
            assertThat(todosItensCardapio.get(i).getNome())
                    .isEqualTo(itensCardapio.get(i).getNome());

            assertThat(todosItensCardapio.get(i).getDescricao())
                    .isEqualTo(itensCardapio.get(i).getDescricao());


            assertThat(todosItensCardapio.get(i).getId())
                    .isEqualTo(itensCardapio.get(i).getId());
        }

        verify(dataSource, times(1)).buscarTodosItensCardapio(idRestaurante);

    }

    @Test
    void devePermitirConsultarItemCardapioPorNome() {
//        String nome = "item teste";
//        Long idItemCardapio = 1L;
//        Long idRestaurante = 99L;
//        var itemCardapio = Helper.gerarItemCardapio();
//        itemCardapio.setId(idItemCardapio);
//        itemCardapio.getRestaurante().setId(idRestaurante);
//
//        when(dataSource.buscarItemCardapioPorNome(nome)).thenReturn(itemCardapio);
//
//        var itemRetornado = gateway.buscarItemCardapioPorNome(nome);
//
//        assertThat(itemRetornado)
//                .isNotNull();
//        assertThat(itemRetornado.getNome())
//                .isEqualTo(itemCardapio.getNome());
//        assertThat(itemRetornado.getId())
//                .isEqualTo(idItemCardapio);
//        assertThat(itemRetornado.getRestaurante().getId())
//                .isEqualTo(idRestaurante);
//        verify(dataSource, times(1)).buscarItemCardapioPorNome(nome);
        fail("Não implementado");
    }


    @Test
    void devePermitirConsultarItemCardapioPorId() {
        Long idItemCardapio = 1L;
        Long idRestaurante = 99L;
        var itemCardapio = Helper.gerarItemCardapio();
        itemCardapio.setId(idItemCardapio);
        itemCardapio.getRestaurante().setId(idRestaurante);

        when(dataSource.buscarItemCardapioPorIdentificador(idItemCardapio)).thenReturn(itemCardapio);

        var itemRetornado = gateway.buscarItemCardapioPorIdentificador(idItemCardapio);

        assertThat(itemRetornado)
                .isNotNull();
        assertThat(itemRetornado.getNome())
                .isEqualTo(itemCardapio.getNome());
        assertThat(itemRetornado.getId())
                .isEqualTo(idItemCardapio);
        assertThat(itemRetornado.getRestaurante().getId())
                .isEqualTo(idRestaurante);
        verify(dataSource, times(1)).buscarItemCardapioPorIdentificador(idItemCardapio);

    }

}
