package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.application.controllers.RestauranteDomainController;
import br.com.fiap.restaurante.core.domain.models.Restaurante;
import br.com.fiap.restaurante.infra.database.repositories.adapter.RestauranteRepositoryAdapter;
import br.com.fiap.restaurante.infra.database.repositories.jpa.RestauranteRepository;
import br.com.fiap.restaurante.infra.doc.RestauranteDocController;
import br.com.fiap.restaurante.infra.dtos.RestauranteDTO;
import br.com.fiap.restaurante.infra.mappers.RestauranteDTOMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurante")
public class RestauranteRestController implements RestauranteDocController {

    private RestauranteDTOMapper restauranteMapper;

    private final RestauranteDomainController domainController;

    public RestauranteRestController(RestauranteDTOMapper restauranteMapper, RestauranteRepository repository) {

        this.restauranteMapper = restauranteMapper;

        domainController = RestauranteDomainController.create(
                    new RestauranteRepositoryAdapter(repository));
    }

    @Override
    @PostMapping
    public ResponseEntity<RestauranteDTO> cadastrar(
                                        @RequestBody @Valid RestauranteDTO dto) {

        Restaurante inputDomain = restauranteMapper.toDomain(dto);

        inputDomain = domainController.cadastrar(inputDomain);

        RestauranteDTO saida = restauranteMapper.toDTO(inputDomain);

        return ResponseEntity.status(HttpStatus.CREATED).body(saida);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<RestauranteDTO> atualizar(
                                        @RequestBody @Valid RestauranteDTO dto,
                                        @PathVariable(required = true) Long id) {

        Restaurante inputDomain = restauranteMapper.toDomain(dto);

        inputDomain = domainController.atualizar(inputDomain, id);

        RestauranteDTO saida = restauranteMapper.toDTO(inputDomain);

        return ResponseEntity.status(HttpStatus.OK).body(saida);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable(required = true) Long id) {

            if(domainController.deletar(id))
                return ResponseEntity.status(HttpStatus.OK).body("Restaurante deletado!");
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi deletado!");
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RestauranteDTO> buscarPorId(
            @PathVariable(required = true) Long id) {

        Restaurante restauranteDomain = domainController.buscarPorId(id);

        RestauranteDTO saida = restauranteMapper.toDTO(restauranteDomain);

        return ResponseEntity.status(HttpStatus.OK).body(saida);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> buscarTodos() {

        List<Restaurante> all = domainController.buscarTodosRestaurantes();

        List<RestauranteDTO> restauranteResponseDTOS = restauranteMapper.toDTO(all);

        return ResponseEntity.status(HttpStatus.OK).body(restauranteResponseDTOS);
    }
}
