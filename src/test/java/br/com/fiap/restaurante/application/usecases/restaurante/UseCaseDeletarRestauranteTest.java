package br.com.fiap.restaurante.application.usecases.restaurante;

import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.application.exceptions.ValidationException;
import br.com.fiap.restaurante.domain.interfaces.gateway.IRestauranteGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class UseCaseDeletarRestauranteTest {

    @Mock
    private IRestauranteGateway gateway;

    private UseCaseDeletarRestaurante useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseDeletarRestaurante.create(gateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirDeletarRestaurante(){
        Long id = 1L;

        when(gateway.deletar(id)).thenReturn(true);

        var isRestauranteDeletado = useCase.run(id);

        assertThat(isRestauranteDeletado).isTrue();
        verify(gateway, times(1)).deletar(id);
    }

    @Test
    void deveFalharAoTentarDeletarRestauranteComIdInvalido(){
        Long id = 1L;

        when(gateway.deletar(id)).thenReturn(false);

        assertThatThrownBy(() -> useCase.run(id))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Restaurante não deletado!");

        verify(gateway, times(1)).deletar(id);
    }
    
}