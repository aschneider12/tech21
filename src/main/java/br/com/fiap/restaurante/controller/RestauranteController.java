package br.com.fiap.restaurante.controller;

import br.com.fiap.restaurante.dtos.RestauranteDTO;
import br.com.fiap.restaurante.dtos.UsuarioRequestDTO;
import br.com.fiap.restaurante.dtos.UsuarioResponseDTO;
import br.com.fiap.restaurante.entities.Usuario;
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
public class RestauranteController implements ICrudRestController<RestauranteDTO, Long> {

    private static final Logger logger = LoggerFactory.getLogger(RestauranteController.class);

    @Autowired
    RestauranteService service;

    @Override
    public ResponseEntity<RestauranteDTO> cadastrar(RestauranteDTO dto) {

        System.out.println(dto);
        return null;
    }

    @Override
    public ResponseEntity<RestauranteDTO> atualizar(RestauranteDTO dto, Long id) {
        System.out.println(dto);
        System.out.println(id);
        return null;
    }

    @Override
    public ResponseEntity<Void> deletar(@PathVariable(required = true) Long id) {
        System.out.println(id);
        return null;
    }

    @Override
    public ResponseEntity<RestauranteDTO> buscarPorId(Long id) {
        System.out.println(id);
        return null;
    }

    @Override
    public ResponseEntity<List<RestauranteDTO>> buscarTodos() {
        List<RestauranteDTO> restauranteDTOS = service.buscarTodosRestaurantes();
        return ResponseEntity.status(HttpStatus.OK).body(restauranteDTOS);
    }


}
