package br.com.fiap.restaurante.controller;

import br.com.fiap.restaurante.dtos.RestauranteDTO;
import br.com.fiap.restaurante.dtos.RestauranteInsertDTO;
import br.com.fiap.restaurante.dtos.RestauranteResponseDTO;
import br.com.fiap.restaurante.dtos.RestauranteUpdateDTO;
import br.com.fiap.restaurante.service.RestauranteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

    private static final Logger logger = LoggerFactory.getLogger(RestauranteController.class);

    @Autowired
    private RestauranteService service;
    @PostMapping
    public ResponseEntity<RestauranteResponseDTO> cadastrar(@RequestBody @Valid RestauranteInsertDTO dto) {

        RestauranteResponseDTO novoRestaurante =  service.cadastrarRestaurante(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoRestaurante);
    }


    @PutMapping("/{id}")
    public ResponseEntity<RestauranteResponseDTO> atualizar(@RequestBody  RestauranteUpdateDTO dto,
                                                            @PathVariable(required = true)  Long id ) {

        RestauranteResponseDTO responseDTO = service.atualizarRestaurante(id,dto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable(required = true) Long id) {
        service.deletarRestaurante(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteResponseDTO> buscarPorId(@PathVariable(required = true) Long id) {

        RestauranteResponseDTO responseDTO = new RestauranteResponseDTO(service
                .buscarRestaurantePorId(id));
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> buscarTodos() {
        List<RestauranteDTO> restauranteDTOS = service.buscarTodosRestaurantes();
        return ResponseEntity.status(HttpStatus.OK).body(restauranteDTOS);
    }


}
