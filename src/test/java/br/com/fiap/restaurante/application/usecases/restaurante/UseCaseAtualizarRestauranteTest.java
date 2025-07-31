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
        restaurante.setId(id);

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

        Restaurante restauranteInputModel = RestauranteInput.toDomain(restauranteInput);
        restauranteInputModel.setId(id);

        when(gateway.buscarRestaurantePorIdentificador(id)).thenReturn(restaurante);
        when(gateway.atualizar(restauranteInputModel)).thenReturn(restauranteInputModel);

        RestauranteOutput restauranteAtualizado = useCase.run(restauranteInput, id);

        assertThat(restauranteAtualizado)
                .isNotNull()
                .isInstanceOf(RestauranteOutput.class);
        assertThat(restauranteAtualizado.id()).isEqualTo(id);
        assertThat(restauranteAtualizado.nome()).isEqualTo(restauranteInputModel.getNome());
        assertThat(restauranteAtualizado.tipoCozinha()).isEqualTo(restauranteInputModel.getTipoCozinha());
        assertThat(restauranteAtualizado.horarioFuncionamento()).isEqualTo(restauranteInputModel.getHorarioFuncionamento());
        assertThat(restauranteAtualizado.dono()).isEqualTo(UsuarioOutput.fromDomain(restauranteInputModel.getDono()));
        assertThat(restauranteAtualizado.endereco()).isEqualTo(EnderecoOutput.fromDomain(restauranteInputModel.getEndereco()));

        verify(gateway, times(1)).buscarRestaurantePorIdentificador(id);
        verify(gateway, times(1)).atualizar(any(Restaurante.class));
    }

    @Test
    void devePermitirAtualizarDadosRestaurante() {
        Long id = 1L;

        var restaurante = Helper.gerarRestaurante();
        restaurante.setId(id);

        String nomeNovo = "Restaurante Explosivo";
        String tipoCozinhaNovo = "Cozinha Explosiva";
        var restauranteInput = RestauranteInput.create(
                nomeNovo,
                tipoCozinhaNovo,
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

        Restaurante restauranteInputModel = RestauranteInput.toDomain(restauranteInput);
        restauranteInputModel.setId(id);

        when(gateway.buscarRestaurantePorIdentificador(id)).thenReturn(restaurante);
        when(gateway.atualizar(restauranteInputModel)).thenReturn(restauranteInputModel);

        RestauranteOutput restauranteAtualizado = useCase.run(restauranteInput, id);

        assertThat(restauranteAtualizado.nome()).isNotEqualTo(restaurante.getNome());
        assertThat(restauranteAtualizado.nome()).isEqualTo(restauranteInputModel.getNome());

        assertThat(restauranteAtualizado.tipoCozinha()).isNotEqualTo(restaurante.getTipoCozinha());
        assertThat(restauranteAtualizado.tipoCozinha()).isEqualTo(restauranteInputModel.getTipoCozinha());

        verify(gateway, times(1)).buscarRestaurantePorIdentificador(id);
        verify(gateway, times(1)).atualizar(any(Restaurante.class));
    }

    @Test
    void deveFalharAoAtualizarRestauranteQueNaoExiste() {
        Long id = 1L;

        var restaurante = Helper.gerarRestaurante();
        restaurante.setId(id);

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
