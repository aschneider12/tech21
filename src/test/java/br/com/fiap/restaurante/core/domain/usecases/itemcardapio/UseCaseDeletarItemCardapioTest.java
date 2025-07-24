package br.com.fiap.restaurante.core.domain.usecases.itemcardapio;

import br.com.fiap.restaurante.core.interfaces.gateway.IItemCardapioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UseCaseDeletarItemCardapioTest {

    @Mock
    private IItemCardapioGateway gateway;

    private UseCaseDeletarItemCardapio useCase;

    @BeforeEach
    void setUp() {
        useCase = new UseCaseDeletarItemCardapio(gateway);
    }

    @Test
    void deveDeletarItemComIdInformado() {
        Long id = 1L;

        // Ação
        useCase.execute(id);

        // Verificação
        verify(gateway).deletar(id);
    }
}
