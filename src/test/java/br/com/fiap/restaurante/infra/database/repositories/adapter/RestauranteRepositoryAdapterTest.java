package br.com.fiap.restaurante.infra.database.repositories.adapter;

import br.com.fiap.restaurante.application.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.domain.models.Restaurante;
import br.com.fiap.restaurante.helper.Helper;
import br.com.fiap.restaurante.infra.database.entities.RestauranteEntity;
import br.com.fiap.restaurante.infra.database.mappers.RestauranteEntityMapper;
import br.com.fiap.restaurante.infra.database.repositories.jpa.RestauranteRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class RestauranteRepositoryAdapterTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    private RestauranteRepositoryAdapter adapter;
    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        adapter = new RestauranteRepositoryAdapter(restauranteRepository);
    }

    @AfterEach
    void teardown() throws Exception{
        mock.close();
    }

    @Test
    void devePermitirCadastrarRestaurante(){
        Long id = 1L;
        var restaurante = Helper.gerarRestaurante();
        restaurante.setId(id);
        var restauranteEntity = RestauranteEntityMapper.INSTANCE.toEntity(restaurante);

        when(restauranteRepository.save(any(RestauranteEntity.class))).thenReturn(restauranteEntity);
        var restauranteArmazenado = adapter.cadastrar(restaurante);

        assertThat(restauranteArmazenado)
                .isNotNull()
                .isInstanceOf(Restaurante.class);

        verify(restauranteRepository, times(1)).save(restauranteEntity);

    }

    @Nested
    class AtualizarRestaurante{
        @Test
        void devePermitirAtualizarRestaurante(){
            var id = 1L;
            var restaurante = Helper.gerarRestaurante();
            restaurante.setId(id);

            var restauranteEntity = RestauranteEntityMapper.INSTANCE.toEntity(restaurante);

            when(restauranteRepository.save(any(RestauranteEntity.class))).thenReturn(restauranteEntity);

            var restauranteAtualizado = adapter.atualizar(restaurante);

            assertThat(restauranteAtualizado)
                    .isNotNull()
                    .isInstanceOf(Restaurante.class);

            verify(restauranteRepository, times(1)).save(restauranteEntity);

        }

        @Test
        void deveFalharAoAtualizarRestauranteComIdNulo(){
            var restaurante = Helper.gerarRestaurante();

            assertThatThrownBy(() -> adapter.atualizar(restaurante))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Não é possível atualizar a entidade sem ID");
        }
    }


    @Nested
    class DeletarRestaurante{
        @Test
        void devePermitirDeletarRestaurante(){
            var id = 1L;

            when(restauranteRepository.existsById(any(Long.class))).thenReturn(true);
            doNothing().when(restauranteRepository).deleteById(any(Long.class));

            boolean restauranteFoiDeletado = adapter.deletar( id);

            assertThat(restauranteFoiDeletado).isTrue();
            verify(restauranteRepository, times(1)).deleteById(id);
            verify(restauranteRepository, times(1)).existsById(id);

        }

        @Test
        void deveFalharAoDeletarRestauranteQueNaoExiste(){
            var id = 1L;

            when(restauranteRepository.existsById(any(Long.class))).thenReturn(false);

            assertThatThrownBy(() -> adapter.deletar(id))
                    .isInstanceOf(EntidadeNaoEncontradaException.class)
                    .hasMessageContaining("Restaurante")
                    .hasMessageContaining("<ID - ")
                    .hasMessageContaining(String.valueOf(id));
            verify(restauranteRepository, never()).deleteById(anyLong());
        }

    }

    @Test
    void devePermitirRetornarTodosOsRestaurantes(){
        List<Restaurante> restaurantes = List.of(
          Helper.gerarRestaurante(),
          Helper.gerarRestaurante(),
          Helper.gerarRestaurante()
        );

        List<RestauranteEntity> restauranteEntities = RestauranteEntityMapper.INSTANCE.toEntity(restaurantes);

        when(restauranteRepository.findAll()).thenReturn(restauranteEntities);

        var todosRestaurantes = adapter.buscarTodosRestaurantes();

        assertThat(todosRestaurantes)
                .isNotNull()
                .hasSize(restaurantes.size());


        for (int i = 0; i < restaurantes.size(); i++) {
            assertThat(todosRestaurantes.get(i).getNome())
                    .isEqualTo(restaurantes.get(i).getNome());

            assertThat(todosRestaurantes.get(i).getTipoCozinha())
                    .isEqualTo(restaurantes.get(i).getTipoCozinha());
        }

        verify(restauranteRepository, times(1)).findAll();

    }

    @Nested
    class ConsultarRestaurantePorId{
        @Test
        void devePermitirConsultarRestaurantePorId(){
            Long id = 1L;
            var restaurante = Helper.gerarRestaurante();
            restaurante.setId(id);
            var restauranteEntity = RestauranteEntityMapper.INSTANCE.toEntity(restaurante);
            when(restauranteRepository.findById(any(Long.class))).thenReturn(Optional.of(restauranteEntity));

            var restauranteObtido = adapter.buscarRestaurantePorIdentificador(id);

            assertThat(restauranteObtido)
                    .isNotNull()
                    .isEqualTo(restaurante);
            verify(restauranteRepository, times(1)).findById(id);

        }

        @Test
        void deveRetornarNuloCasoNaoEncontreRestaurantePorId(){
            Long id = 1L;
            when(restauranteRepository.findById(any(Long.class))).thenReturn(Optional.empty());

            var restauranteObtido = adapter.buscarRestaurantePorIdentificador(id);

            assertThat(restauranteObtido)
                    .isNull();
            verify(restauranteRepository, times(1)).findById(id);

        }
    }


    @Nested
    class ConsultarRestaurantePorNome{
        @Test
        void devePermitirConsultarRestaurantePorNome(){
            String nome = "Restaurante teste";
            var restaurante = Helper.gerarRestaurante();
            var restauranteEntity = RestauranteEntityMapper.INSTANCE.toEntity(restaurante);


            when(restauranteRepository.findByNome(any(String.class))).thenReturn(Optional.of(restauranteEntity));

            var restauranteObtido = adapter.buscarRestaurantePorNome(nome);

            assertThat(restauranteObtido)
                    .isNotNull()
                    .isEqualTo(restaurante);
            verify(restauranteRepository, times(1)).findByNome(nome);

        }

        @Test
        void deveRetornarNuloCasoNaoEncontreRestauranteNome(){
            String nome = "Restaurante teste";
            when(restauranteRepository.findByNome(any(String.class))).thenReturn(Optional.empty());

            var restauranteObtido = adapter.buscarRestaurantePorNome(nome);

            assertThat(restauranteObtido).isNull();
            verify(restauranteRepository, times(1)).findByNome(nome);

        }
    }

}
