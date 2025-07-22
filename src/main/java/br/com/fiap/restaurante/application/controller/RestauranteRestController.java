package br.com.fiap.restaurante.application.controller;

import br.com.fiap.restaurante.application.doc.RestauranteDocController;
import br.com.fiap.restaurante.application.dtos.RestauranteInsertDTO;
import br.com.fiap.restaurante.application.dtos.RestauranteResponseDTO;
import br.com.fiap.restaurante.application.dtos.RestauranteUpdateDTO;
import br.com.fiap.restaurante.application.mappers.RestauranteMapper;
import br.com.fiap.restaurante.application.repositories.adapter.RestauranteRepositoryAdapter;
import br.com.fiap.restaurante.application.repositories.jpa.RestauranteRepository;
import br.com.fiap.restaurante.core.controllers.RestauranteDomainController;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteInputDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteOutputDTO;
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
public class RestauranteRestController implements RestauranteDocController {

    private static final Logger logger = LoggerFactory.getLogger(RestauranteDomainController.class);

    @Autowired
    RestauranteMapper restauranteMapper;

    RestauranteDomainController domainController;

    public RestauranteRestController(RestauranteRepository repository) {

        domainController = RestauranteDomainController.create(
                    new RestauranteRepositoryAdapter(repository));
    }

    //nao poderia ser usado
    @PostMapping
    public ResponseEntity<RestauranteResponseDTO> cadastrar(@RequestBody @Valid RestauranteInsertDTO dto) {

        RestauranteInputDTO iinputDomain = restauranteMapper.toIinputDomain(dto);

        RestauranteOutputDTO cadastrado = domainController.cadastrar(iinputDomain);

        RestauranteResponseDTO saida = restauranteMapper.toClient(cadastrado);

        return ResponseEntity.status(HttpStatus.CREATED).body(saida);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<RestauranteResponseDTO> atualizar(
            @RequestBody RestauranteUpdateDTO dto, @PathVariable(required = true) Long id) {


        RestauranteInputDTO iinputDomain = restauranteMapper.toIinputDomain(dto);

        RestauranteOutputDTO cadastrado = domainController.cadastrar(iinputDomain);

        RestauranteResponseDTO saida = restauranteMapper.toClient(cadastrado);

        return ResponseEntity.status(HttpStatus.OK).body(saida);
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
        RestauranteOutputDTO dto = domainController.buscarPorId(id);

        RestauranteResponseDTO client = restauranteMapper.toClient(dto);

        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @GetMapping
    public ResponseEntity<List<RestauranteResponseDTO>> buscarTodos() {

        List<RestauranteOutputDTO> all = domainController.buscarTodosRestaurantes();

        List<RestauranteResponseDTO> restauranteResponseDTOS = restauranteMapper.toClientList(all);

//        List<RestauranteResponseDTO> restauranteResponseDTOS = all.stream().map(
//                d -> new RestauranteResponseDTO(d.id(), d.nome(), d.tipoCozinha(), d.horarioFuncionamento())).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(restauranteResponseDTOS);
    }


}
