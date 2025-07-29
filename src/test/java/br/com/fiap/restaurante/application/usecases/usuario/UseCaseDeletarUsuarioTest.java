package br.com.fiap.restaurante.application.usecases.usuario;

import br.com.fiap.restaurante.application.exceptions.ValidationException;
import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class UseCaseDeletarUsuarioTest {


    @Mock
    private IUsuarioGateway gateway;

    private UseCaseDeletarUsuario useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseDeletarUsuario.create(gateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirDeletarUsuario(){
        Long id = 1L;

        when(gateway.deletar(id)).thenReturn(true);

        var usuarioFoiDeletado = useCase.run(id);

        assertThat(usuarioFoiDeletado).isTrue();
        verify(gateway, times(1)).deletar(id);
    }

    @Test
    void deveFalharAoTentarDeletarUsuarioComIdInvalido(){
        Long id = 1L;

        when(gateway.deletar(id)).thenReturn(false);

        assertThatThrownBy(() -> useCase.run(id))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Usuário não deletado!");
        verify(gateway, times(1)).deletar(id);
    }
}
