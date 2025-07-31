package br.com.fiap.restaurante.application.usecases.usuario.senha;

import br.com.fiap.restaurante.domain.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.restaurante.infra.security.JwtService;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UseCaseValidarTokenTest {

    @Mock
    private IUsuarioGateway gateway;

    @Mock
    private JwtService jwtService;

    private UseCaseValidarToken useCase;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        useCase = UseCaseValidarToken.create(gateway);
        useCase.setJwtService(jwtService); // Injeta mock
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Test
    void deveValidarTokenCorreto() {
        String token = "Bearer valido";

        when(jwtService.validarToken(token)).thenReturn(true);

        boolean valido = useCase.run(token);

        assertThat(valido).isTrue();
        verify(jwtService, times(1)).validarToken(token);
    }

    @Test
    void deveRejeitarTokenInvalido() {
        String token = "Bearer invalido";

        when(jwtService.validarToken(token)).thenReturn(false);

        boolean valido = useCase.run(token);

        assertThat(valido).isFalse();
        verify(jwtService, times(1)).validarToken(token);
    }
}
