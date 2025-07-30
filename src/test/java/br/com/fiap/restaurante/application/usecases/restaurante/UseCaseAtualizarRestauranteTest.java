package br.com.fiap.restaurante.application.usecases.restaurante;

import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.application.input.EnderecoInput;
import br.com.fiap.restaurante.application.input.RestauranteInput;
import br.com.fiap.restaurante.application.output.EnderecoOutput;
import br.com.fiap.restaurante.application.output.RestauranteOutput;
import br.com.fiap.restaurante.application.output.UsuarioOutput;
import br.com.fiap.restaurante.domain.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.domain.models.Restaurante;
import br.com.fiap.restaurante.helper.Helper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UseCaseAtualizarRestauranteTest {

    @Mock
    private IRestauranteGateway gateway;

    private UseCaseAtualizarRestaurante useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseAtualizarRestaurante.create(gateway);
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirAtualizarRestaurante() {
        Long id = 1L;

        var restaurante = Helper.gerarRestaurante();
        var restauranteInput = RestauranteInput.create(
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento(),
                EnderecoInput.create(
                        restaurante.getEndereco().getId(),
                        restaurante.getEndereco().getRua(),
                        restaurante.getEndereco().getNumero(),
                        restaurante.getEndereco().getCidade(),
                        restaurante.getEndereco().getEstado(),
                        restaurante.getEndereco().getCep()
                ),
                restaurante.getDono().getId()
        );
        restaurante.setId(id);

        when(gateway.buscarRestaurantePorIdentificador(id)).thenReturn(restaurante);
        when(gateway.atualizar(RestauranteInput.toDomain(restauranteInput))).thenReturn(restaurante);

        RestauranteOutput restauranteAtualizado = useCase.run(restauranteInput, id);

        assertThat(restauranteAtualizado)
                .isNotNull()
                .isInstanceOf(RestauranteOutput.class);
        assertThat(restauranteAtualizado.id()).isEqualTo(id);
        assertThat(restauranteAtualizado.nome()).isEqualTo(restaurante.getNome());
        assertThat(restauranteAtualizado.tipoCozinha()).isEqualTo(restaurante.getTipoCozinha());
        assertThat(restauranteAtualizado.horarioFuncionamento()).isEqualTo(restaurante.getHorarioFuncionamento());
        assertThat(restauranteAtualizado.dono()).isEqualTo(UsuarioOutput.fromDomain(restaurante.getDono()));
        assertThat(restauranteAtualizado.endereco()).isEqualTo(EnderecoOutput.fromDomain(restaurante.getEndereco()));

        verify(gateway, times(1)).buscarRestaurantePorIdentificador(id);
        verify(gateway, times(1)).atualizar(any(Restaurante.class));
    }

    @Test
    void deveFalharAoAtualizarRestauranteQueNaoExiste() {
        Long id = 1L;

        var restaurante = Helper.gerarRestaurante();
        var restauranteInput = RestauranteInput.create(
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento(),
                EnderecoInput.create(
                        restaurante.getEndereco().getId(),
                        restaurante.getEndereco().getRua(),
                        restaurante.getEndereco().getNumero(),
                        restaurante.getEndereco().getCidade(),
                        restaurante.getEndereco().getEstado(),
                        restaurante.getEndereco().getCep()
                ),
                restaurante.getDono().getId()
        );
        restaurante.setId(id);

        when(gateway.buscarRestaurantePorIdentificador(id)).thenReturn(null);

        assertThatThrownBy(() -> useCase.run(restauranteInput, id))
                .isInstanceOf(EntidadeNaoEncontradaException.class)
                .hasMessageContaining("Restaurante")
                .hasMessageContaining("ID")
                .hasMessageContaining(String.valueOf(id));

        verify(gateway, times(1)).buscarRestaurantePorIdentificador(id);
        verify(gateway, never()).atualizar(any(Restaurante.class));
    }
}
