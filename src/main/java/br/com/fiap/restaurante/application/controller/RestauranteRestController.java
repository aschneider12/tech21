package br.com.fiap.restaurante.application.controller;

import br.com.fiap.restaurante.application.dtos.RestauranteInsertDTO;
import br.com.fiap.restaurante.application.dtos.RestauranteResponseDTO;
import br.com.fiap.restaurante.application.dtos.RestauranteUpdateDTO;
import br.com.fiap.restaurante.application.repositories.adapter.RestauranteRepositoryAdapter;
import br.com.fiap.restaurante.application.doc.RestauranteDocController;
import br.com.fiap.restaurante.application.repositories.jpa.RestauranteRepository;
import br.com.fiap.restaurante.core.controllers.RestauranteDomainController;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteCadastroDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteRetornoDTO;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
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
    ModelMapper mp = new ModelMapper();
    RestauranteDomainController domainController;

    public RestauranteRestController(RestauranteRepository repository) {

        domainController = RestauranteDomainController.create(
                    new RestauranteRepositoryAdapter(repository));
    }

    //nao poderia ser usado
    @PostMapping
    public ResponseEntity<RestauranteResponseDTO> cadastrar(@RequestBody @Valid RestauranteInsertDTO dto) {

//        --> map do insert para o cadastro dto aqui;


        RestauranteCadastroDTO maped = mp.map(dto, RestauranteCadastroDTO.class);

        RestauranteRetornoDTO cadastrado = domainController.cadastrar(maped);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<RestauranteResponseDTO> atualizar(
            @RequestBody RestauranteUpdateDTO dto, @PathVariable(required = true) Long id) {

        RestauranteCadastroDTO maped = mp.map(dto, RestauranteCadastroDTO.class);

        RestauranteRetornoDTO atualizado = domainController.atualizar(maped, id);

        RestauranteResponseDTO as = mp.map(atualizado, RestauranteResponseDTO.class);

        return ResponseEntity.status(HttpStatus.OK).body(as);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable(required = true) Long id) {
        try {

            boolean removido = domainController.deletar(id);

        } catch(Exception ex) {

            ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado!");
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RestauranteResponseDTO> buscarPorId(@PathVariable(required = true) Long id) {
        RestauranteRetornoDTO dto = domainController.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping
    public ResponseEntity<List<RestauranteResponseDTO>> buscarTodos() {

        List<RestauranteRetornoDTO> all = domainController.buscarTodosRestaurantes();

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


}
