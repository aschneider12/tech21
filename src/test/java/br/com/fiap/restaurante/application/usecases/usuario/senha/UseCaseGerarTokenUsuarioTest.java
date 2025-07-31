package br.com.fiap.restaurante.application.usecases.usuario.senha;

import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.infra.security.JwtService;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UseCaseGerarTokenUsuarioTest {

    @Mock
    private IUsuarioGateway gateway;

    @Mock
    private JwtService jwtService;

    private UseCaseGerarTokenUsuario useCase;

    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseGerarTokenUsuario.create(gateway, jwtService);
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Test
    void deveRetornarNullSeJwtServiceRetornarNull() {
        String login = "usuario123";

        when(jwtService.gerarToken(login)).thenReturn(null);

        String token = useCase.run(login);

        assertThat(token).isNull();
        verify(jwtService, times(1)).gerarToken(login);
    }

    @Test
    void deveGerarTokenParaUsuario(){
        String login = "usuario123";
        String tokenEsperado = "fake.jwt.token";

        when(jwtService.gerarToken(login)).thenReturn(tokenEsperado);

        String token = useCase.run(login);

        assertThat(token)
                .isNotNull()
                .isEqualTo(tokenEsperado);

        verify(jwtService, times(1)).gerarToken(login);
    }
}
