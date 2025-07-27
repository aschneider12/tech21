//package br.com.fiap.restaurante.domain.usecases.restaurante;
//
//import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
//import br.com.fiap.restaurante.application.usecases.restaurante.UseCaseBuscarRestaurantePorID;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class UseCaseBuscarRestaurantePorIDTest {
//
//    @Mock
//    private IRestauranteGateway gateway;
//
//    private UseCaseBuscarRestaurantePorID useCase;
//
//    @BeforeEach
//    void setUp() {
//        useCase = UseCaseBuscarRestaurantePorID.create(gateway);
//    }
//
//    @Test
//    void deveRetornarRestauranteQuandoIdExistir() throws EntidadeNaoEncontradaException {
//
//        Long id = 1L;
//        Restaurante restauranteEsperado =
//                Restaurante.create(id, "Restaurante Teste", "Italiana", "10h-22h", null, null, null);
//
//        when(gateway.buscarRestaurantePorIdentificador(id)).thenReturn(restauranteEsperado);
//
//        RestauranteOutputDTO resultado = useCase.run(id);
//
//        assertNotNull(resultado);
//        assertEquals("Restaurante Teste", resultado.nome());
//        verify(gateway).buscarRestaurantePorIdentificador(id);
//    }
//
//    @Test
//    void deveLancarExcecaoQuandoRestauranteNaoExistir() {
//        Long id = 99L;
//        when(gateway.buscarRestaurantePorIdentificador(id)).thenReturn(null);
//
//        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
//            useCase.run(id);
//        });
//
//        assertTrue(exception.getMessage().contains("Restaurante"));
//        assertTrue(exception.getMessage().contains("99"));
//        verify(gateway).buscarRestaurantePorIdentificador(id);
//    }
//}