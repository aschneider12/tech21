package br.com.fiap.restaurante.application.usecases.restaurante;

import br.com.fiap.restaurante.application.exceptions.EntidadeJaExisteException;
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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UseCaseCadastrarRestauranteTest {

    @Mock
    private IRestauranteGateway gateway;

    private UseCaseCadastrarRestaurante useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseCadastrarRestaurante.create(gateway);
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirCadastrarRestaurante() {

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

        Restaurante restauranteInputModel = RestauranteInput.toDomain(restauranteInput);

        when(gateway.buscarRestaurantePorNome(restauranteInputModel.getNome())).thenReturn(null);
        when(gateway.cadastrar(restauranteInputModel)).thenReturn(restaurante);

        RestauranteOutput restauranteCadastrado = useCase.run(restauranteInput);

        assertThat(restauranteCadastrado)
                .isNotNull()
                .isInstanceOf(RestauranteOutput.class);
        assertThat(restauranteCadastrado.id()).isEqualTo(restaurante.getId());
        assertThat(restauranteCadastrado.nome()).isEqualTo(restaurante.getNome());
        assertThat(restauranteCadastrado.tipoCozinha()).isEqualTo(restaurante.getTipoCozinha());
        assertThat(restauranteCadastrado.horarioFuncionamento()).isEqualTo(restaurante.getHorarioFuncionamento());
        assertThat(restauranteCadastrado.dono()).isEqualTo(UsuarioOutput.fromDomain(restaurante.getDono()));
        assertThat(restauranteCadastrado.endereco()).isEqualTo(EnderecoOutput.fromDomain(restaurante.getEndereco()));

        verify(gateway, times(1)).buscarRestaurantePorNome(restauranteInputModel.getNome());
        verify(gateway, times(1)).cadastrar(any(Restaurante.class));
    }

    @Test
    void deveFalharAoCadastrarRestauranteComMesmoNome() {

        var restaurante = Helper.gerarRestaurante();

        String tipoCozinhaNovo = "Cozinha Explosiva";
        String horarioFuncionamentoNovo = "Madrugada";
        var restauranteInput = RestauranteInput.create(
                restaurante.getNome(),
                tipoCozinhaNovo,
                horarioFuncionamentoNovo,
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

        when(gateway.buscarRestaurantePorNome(restauranteInputModel.getNome())).thenReturn(restaurante);
        when(gateway.cadastrar(restauranteInputModel)).thenReturn(restaurante);

        assertThatThrownBy(() -> useCase.run(restauranteInput))
                .isInstanceOf(EntidadeJaExisteException.class)
                .hasMessageContaining("Restaurante")
                .hasMessageContaining(restauranteInputModel.getNome());

        verify(gateway, times(1)).buscarRestaurantePorNome(restauranteInputModel.getNome());
        verify(gateway, never()).cadastrar(any(Restaurante.class));
    }

}