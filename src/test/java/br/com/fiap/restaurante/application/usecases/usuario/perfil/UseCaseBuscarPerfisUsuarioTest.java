package br.com.fiap.restaurante.application.usecases.usuario.perfil;

import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.helper.Helper;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UseCaseBuscarPerfisUsuarioTest {

    @Mock
    private IUsuarioGateway gateway;

    private UseCaseBuscarPerfisUsuario useCase;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseBuscarPerfisUsuario.create(gateway);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirBuscarPerfisDoUsuario(){
        Long id = 1L;
        var usuario = Helper.gerarUsuario();
        usuario.setId(id);
        var perfis = new HashSet<String>();
        perfis.add("dono");
        usuario.setPerfis(perfis);

        when(gateway.buscarUsuarioPorIdentificador(id)).thenReturn(usuario);

        var perfisBuscados = useCase.run(id);

        assertThat(perfis)
                .isEqualTo(perfisBuscados);
        verify(gateway , times(1)).buscarUsuarioPorIdentificador(id);
    }
}
