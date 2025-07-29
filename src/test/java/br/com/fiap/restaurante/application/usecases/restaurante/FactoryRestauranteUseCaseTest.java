//package br.com.fiap.restaurante.application.usecases.restaurante;
//
//import br.com.fiap.restaurante.domain.interfaces.gateway.IRestauranteGateway;
//import br.com.fiap.restaurante.infra.database.repositories.adapter.RestauranteRepositoryAdapter;
//import br.com.fiap.restaurante.infra.database.repositories.jpa.RestauranteRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.assertj.core.api.Assertions.fail;
//
//public class FactoryRestauranteUseCaseTest {
//    @Mock
//    private IRestauranteGateway gateway;
//
//    private FactoryRestauranteUseCase useCase;
//    AutoCloseable mock;
//
//    @BeforeEach
//    void setup(){
//        mock = MockitoAnnotations.openMocks(this);
//        useCase = new FactoryRestauranteUseCase(gateway);
//    }
//
//    @AfterEach
//    void teardown() throws Exception{
//        mock.close();
//    }
//
//    @Test
//    void devePermitirBuscarTodosRestaurantes(){
//        fail("Não implementado");
//    }
//
//    @Test
//    void devePermitirbuscarRestaurantePorId(){
//        fail("Não implementado");
//    }
//
//    @Test
//    void devePermitirDeletarRestaurante(){
//        fail("Não implementado");
//    }
//
//    @Test
//    void devePermitirCadastrarRestaurante(){
//        fail("Não implementado");
//    }
//
//    @Test
//    void devePermitirAtualizarRestaurante(){
//        fail("Não implementado");
//    }
//
//
//}
