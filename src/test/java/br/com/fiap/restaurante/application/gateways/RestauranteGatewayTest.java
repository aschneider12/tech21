package br.com.fiap.restaurante.application.gateways;

import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.domain.interfaces.storage.IDataStorageRestaurante;
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
import static org.mockito.Mockito.times;

public class RestauranteGatewayTest {

    @Mock
    private IDataStorageRestaurante dataSource;

    private RestauranteGateway gateway;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        gateway = RestauranteGateway.create(dataSource);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirCadastrarRestaurante(){
        Long id = 1L;
        var restaurante = Helper.gerarRestaurante();
        restaurante.setId(id);

        when(dataSource.cadastrar(restaurante)).thenReturn(restaurante);


        var restauranteCadastrado = gateway.cadastrar(restaurante);

        assertThat(restauranteCadastrado)
                .isNotNull()
                .isInstanceOf(Restaurante.class)
                .isEqualTo(restaurante);

        verify(dataSource, times(1)).cadastrar(restaurante);

    }


    @Test
    void devePermitirAtualizarRestaurante(){
        var id = 1L;
        var restaurante = Helper.gerarRestaurante();
        restaurante.setId(id);


        when(dataSource.atualizar(restaurante)).thenReturn(restaurante);

        var restauranteAtualizado = gateway.atualizar(restaurante);

        assertThat(restauranteAtualizado)
                .isNotNull()
                .isInstanceOf(Restaurante.class);

        verify(dataSource, times(1)).atualizar(restaurante);

    }


    @Test
    void devePermitirDeletarRestaurante(){
        var id = 1L;

        when(dataSource.deletar(id)).thenReturn(true);

        boolean restauranteFoiDeletado = gateway.deletar( id);

        assertThat(restauranteFoiDeletado).isTrue();
        verify(dataSource, times(1)).deletar(id);

    }



    @Test
    void devePermitirRetornarTodosOsRestaurantes(){
        List<Restaurante> restaurantes = List.of(
                Helper.gerarRestaurante(),
                Helper.gerarRestaurante(),
                Helper.gerarRestaurante()
        );


        when(dataSource.buscarTodosRestaurantes()).thenReturn(restaurantes);

        var todosRestaurantes = gateway.buscarTodosRestaurantes();

        assertThat(todosRestaurantes)
                .isNotNull()
                .hasSize(restaurantes.size());


        for (int i = 0; i < restaurantes.size(); i++) {
            assertThat(todosRestaurantes.get(i).getNome())
                    .isEqualTo(restaurantes.get(i).getNome());

            assertThat(todosRestaurantes.get(i).getTipoCozinha())
                    .isEqualTo(restaurantes.get(i).getTipoCozinha());
        }

        verify(dataSource, times(1)).buscarTodosRestaurantes();

    }

    @Test
    void devePermitirConsultarRestaurantePorNome() {
        String nome = "Restaurante teste";
        var restaurante = Helper.gerarRestaurante();

        when(dataSource.buscarRestaurantePorNome(nome)).thenReturn(restaurante);

        var restauranteObtido = gateway.buscarRestaurantePorNome(nome);

        assertThat(restauranteObtido)
                .isNotNull()
                .isEqualTo(restaurante);
        verify(dataSource, times(1)).buscarRestaurantePorNome(nome);

    }


    @Test
    void devePermitirConsultarRestaurantePorId(){
        Long id = 1L;
        var restaurante = Helper.gerarRestaurante();
        restaurante.setId(id);

        when(dataSource.buscarRestaurantePorIdentificador(id)).thenReturn(restaurante);

        var restauranteObtido = gateway.buscarRestaurantePorIdentificador(id);

        assertThat(restauranteObtido)
                .isNotNull()
                .isEqualTo(restaurante);
        verify(dataSource, times(1)).buscarRestaurantePorIdentificador(id);

    }

}
