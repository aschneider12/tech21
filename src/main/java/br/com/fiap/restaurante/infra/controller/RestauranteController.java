package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.application.gateways.RestauranteGateway;
import br.com.fiap.restaurante.application.input.RestauranteInput;
import br.com.fiap.restaurante.application.output.RestauranteOutput;
import br.com.fiap.restaurante.application.usecases.restaurante.FactoryRestauranteUseCase;
import br.com.fiap.restaurante.application.usecases.restaurante.UseCaseAtualizarRestaurante;
import br.com.fiap.restaurante.application.usecases.restaurante.UseCaseBuscarTodosRestaurantes;
import br.com.fiap.restaurante.application.usecases.restaurante.UseCaseCadastrarRestaurante;
import br.com.fiap.restaurante.infra.database.repositories.adapter.RestauranteRepositoryAdapter;
import br.com.fiap.restaurante.infra.database.repositories.jpa.RestauranteRepository;
import br.com.fiap.restaurante.infra.doc.RestauranteDocController;
import br.com.fiap.restaurante.infra.dtos.RestauranteDTO;
import br.com.fiap.restaurante.infra.mappers.RestauranteDTOMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController implements RestauranteDocController {

    private final FactoryRestauranteUseCase factoryRestauranteUseCase;

    public RestauranteController(FactoryRestauranteUseCase factory ) {

        factoryRestauranteUseCase = factory;
    }

    @Override
    @PostMapping
    public ResponseEntity<RestauranteDTO> cadastrar(
                                        @RequestBody @Valid RestauranteDTO dto) {

        RestauranteInput input = RestauranteDTOMapper.INSTANCE.toInputApplication(dto);

       var uc = factoryRestauranteUseCase.cadastrarRestaurante();

       var output  = uc.run(input);

        RestauranteDTO outputDTO = RestauranteDTOMapper.INSTANCE.toDTO(output);

        return ResponseEntity.status(HttpStatus.CREATED).body(outputDTO);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<RestauranteDTO> atualizar(
                                        @RequestBody @Valid RestauranteDTO dto,
                                        @PathVariable(required = true) Long id) {

        var uc = factoryRestauranteUseCase.atualizarRestaurante();

        RestauranteInput input = RestauranteDTOMapper.INSTANCE.toInputApplication(dto);
        var atualizado = uc.run(input, id);

        RestauranteDTO saida = RestauranteDTOMapper.INSTANCE.toDTO(atualizado);

        return ResponseEntity.status(HttpStatus.OK).body(saida);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable(required = true) Long id) {

        var uc = factoryRestauranteUseCase.deletarRestaurante();

        if(uc.run(id))
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Restaurante deletado!");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi deletado!");
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RestauranteDTO> buscarPorId(
            @PathVariable(required = true) Long id) {

        var uc = factoryRestauranteUseCase.buscarRestaurantePorId();
        RestauranteOutput restauranteOutput = uc.run(id);

        RestauranteDTO saida = RestauranteDTOMapper.INSTANCE.toDTO(restauranteOutput);

        return ResponseEntity.status(HttpStatus.OK).body(saida);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> buscarTodos() {

        var uc = factoryRestauranteUseCase.buscarTodosRestaurantes();

        List<RestauranteOutput> all = uc.run();

        List<RestauranteDTO> restauranteResponseDTOS = RestauranteDTOMapper.INSTANCE.toDTO(all);

        return ResponseEntity.status(HttpStatus.OK).body(restauranteResponseDTOS);
    }
}
