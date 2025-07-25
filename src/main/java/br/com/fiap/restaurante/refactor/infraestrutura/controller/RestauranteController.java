package br.com.fiap.restaurante.refactor.infraestrutura.controller;

import br.com.fiap.restaurante.RestaurantesApi;
import br.com.fiap.restaurante.gen.model.RestauranteDto;
import br.com.fiap.restaurante.refactor.core.usecase.InserirRestauranteUseCase;
import br.com.fiap.restaurante.refactor.domain.model.RestauranteDomain;
import br.com.fiap.restaurante.refactor.infraestrutura.controller.mapper.RestauranteDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class RestauranteController implements RestaurantesApi {

    private final InserirRestauranteUseCase restaurante;

    public RestauranteController(InserirRestauranteUseCase restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public ResponseEntity<Void> _inserirRestaurante(RestauranteDto restauranteDto) {
        RestauranteDomain domain = RestauranteDtoMapper.INSTANCE.toRestauranteDomain(restauranteDto);
        restaurante.inserir(domain);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
