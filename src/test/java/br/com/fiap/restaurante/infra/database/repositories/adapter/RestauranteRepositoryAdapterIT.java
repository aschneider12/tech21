package br.com.fiap.restaurante.infra.database.repositories.adapter;

import br.com.fiap.restaurante.domain.models.Restaurante;
import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.helper.Helper;
import br.com.fiap.restaurante.infra.database.entities.UsuarioEntity;
import br.com.fiap.restaurante.infra.database.mappers.RestauranteEntityMapper;
import br.com.fiap.restaurante.infra.database.mappers.UsuarioEntityMapper;
import br.com.fiap.restaurante.infra.database.repositories.jpa.RestauranteRepository;
import br.com.fiap.restaurante.infra.database.repositories.jpa.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@AutoConfigureTestDatabase
@Transactional
@Sql(scripts = {"/db_restaurante_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = {"/db_restaurante_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class RestauranteRepositoryAdapterIT {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RestauranteRepositoryAdapter adapter;

    @Test
    void devePermtirCadastrarRestaurante(){


        UsuarioEntity usuarioEntity = usuarioRepository.findById(9999L)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Usuario dono = UsuarioEntityMapper.INSTANCE.toDomain(usuarioEntity);

        var restaurante = Helper.gerarRestaurante();
        restaurante.getEndereco().setId(null);
        restaurante.setDono(dono);

        var restauranteCadastrado = adapter.cadastrar(restaurante);

        assertThat(restauranteCadastrado)
                .isNotNull()
                .isInstanceOf(Restaurante.class);

        assertThat(restauranteCadastrado.getNome())
                .isEqualTo(restaurante.getNome());

        assertThat(restauranteCadastrado.getTipoCozinha())
                .isEqualTo(restaurante.getTipoCozinha());

        assertThat(restauranteCadastrado.getDono().getId())
                .isEqualTo(restaurante.getDono().getId());

    }

    @Test
    void devePermitirAtualizarRestaurante() {

        var restauranteExistenteEntity = restauranteRepository.findById(9999L)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado para atualização"));

        var restauranteExistente = RestauranteEntityMapper.INSTANCE.toDomain(restauranteExistenteEntity);


        String novoNome = "Nome Atualizado";
        String novoTipoCozinha = "Italiana";
        restauranteExistente.setNome(novoNome);
        restauranteExistente.setTipoCozinha(novoTipoCozinha);


        var restauranteAtualizado = adapter.atualizar(restauranteExistente);


        assertThat(restauranteAtualizado)
                .isNotNull();

        assertThat(restauranteAtualizado.getId())
                .isEqualTo(restauranteExistente.getId());

        assertThat(restauranteAtualizado.getNome())
                .isEqualTo(novoNome);

        assertThat(restauranteAtualizado.getTipoCozinha())
                .isEqualTo(novoTipoCozinha);
    }


    @Test
    void devePermitirDeletarRestaurante(){
        Long id = 9999L;

        var restauranteFoiRemovido = adapter.deletar(id);

        assertThat(restauranteFoiRemovido)
                .isTrue();
    }

    @Test
    void devePermtirBuscarTodosRestaurantes() {
        List<Restaurante> restaurantes = adapter.buscarTodosRestaurantes();

        assertThat(restaurantes)
                .isNotNull();
        assertThat(restaurantes)
                .isNotEmpty();
    }

    @Test
    void devePermitirBuscarRestaurantePorId(){
        Long id = 9999L;

        var restauranteEncontrado = adapter.buscarRestaurantePorIdentificador(id);

        assertThat(restauranteEncontrado)
                .isNotNull()
                .isInstanceOf(Restaurante.class);
    }

    @Test
    void devePermitirBuscarRestaurantePorNome(){
       String nome = "Restaurante teste";

        var restauranteEncontrado = adapter.buscarRestaurantePorNome(nome);

        assertThat(restauranteEncontrado)
                .isNotNull()
                .isInstanceOf(Restaurante.class);
    }


}
