package br.com.fiap.restaurante.application.controller;

import br.com.fiap.restaurante.application.adapter.RestauranteRepositoryAdapter;
import br.com.fiap.restaurante.application.doc.RestauranteDocController;
import br.com.fiap.restaurante.application.repositories.RestauranteRepository;
import br.com.fiap.restaurante.core.controllers.RestauranteDomainController;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteCadastroDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteRetornoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurante")
public class RestauranteRestController implements RestauranteDocController {

    private static final Logger logger = LoggerFactory.getLogger(RestauranteDomainController.class);

    RestauranteDomainController domainController;

    public RestauranteRestController(RestauranteRepository repository) {

        domainController = RestauranteDomainController.create(
                    new RestauranteRepositoryAdapter(repository));
    }

    @PostMapping
    public ResponseEntity<RestauranteRetornoDTO> cadastrar(RestauranteCadastroDTO dto) {

        RestauranteRetornoDTO cadastrado = domainController.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrado);
    }


    @Override
    public ResponseEntity<RestauranteRetornoDTO> atualizar(RestauranteCadastroDTO dto, Long id) {

        RestauranteRetornoDTO atualizado = domainController.atualizar(dto, id);

        return ResponseEntity.status(HttpStatus.OK).body(atualizado);
    }

    @Override
    public ResponseEntity<String> deletar(@PathVariable(required = true) Long id) {
        try {
            boolean removido = domainController.deletar(id);

        } catch(Exception ex) {

            ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado!");
    }

    @Override
    public ResponseEntity<RestauranteRetornoDTO> buscarPorId(Long id) {
        RestauranteRetornoDTO dto = domainController.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<RestauranteRetornoDTO>> buscarTodos() {

        List<RestauranteRetornoDTO> all = domainController.buscarTodosRestaurantes();

        return ResponseEntity.status(HttpStatus.OK).body(all);
    }


}
