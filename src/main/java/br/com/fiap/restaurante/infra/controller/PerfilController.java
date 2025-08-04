package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.application.gateways.UsuarioGateway;
import br.com.fiap.restaurante.application.usecases.usuario.FactoryUsuarioUseCase;
import br.com.fiap.restaurante.application.usecases.usuario.perfil.UseCaseAdicionarPerfisUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.perfil.UseCaseBuscarPerfisUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.perfil.UseCaseRemoverPerfisUsuario;
import br.com.fiap.restaurante.infra.database.entities.TipoUsuario;
import br.com.fiap.restaurante.infra.database.repositories.adapter.UsuarioRepositoryAdapter;
import br.com.fiap.restaurante.infra.database.repositories.jpa.UsuarioRepository;
import br.com.fiap.restaurante.infra.doc.PerfilDocController;
import br.com.fiap.restaurante.infra.dtos.PerfilRequestDTO;
import br.com.fiap.restaurante.infra.dtos.PerfilResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario/{usuarioId}/perfil")
public class PerfilController implements PerfilDocController {

//    private static final Logger logger = LoggerFactory.getLogger(PerfilController.class);

    private final FactoryUsuarioUseCase factory;

    public PerfilController(FactoryUsuarioUseCase factoryUsuarioUseCase) {
        factory = factoryUsuarioUseCase;
    }

    @Override
    @GetMapping
    public ResponseEntity<PerfilResponseDTO> listarPerfis(@PathVariable Long usuarioId) {

        var uc = factory.buscarPerfisUsuario();

        Set<String> perfis = uc.run(usuarioId);

        List<TipoUsuario> perfisEnum = perfis.stream().map(TipoUsuario::valueOf).toList();

        return ResponseEntity.ok(new PerfilResponseDTO("ID - "+usuarioId, perfisEnum));
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> adicionarPerfis(
            @PathVariable Long usuarioId,
            @RequestBody PerfilRequestDTO dto) {

        Set<String> perfisString = dto.perfis().stream().map(Enum::name).collect(Collectors.toSet());

        var uc = factory.adicionarPerfisUsuario();
        uc.run(usuarioId, perfisString);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> removerPerfis(
            @PathVariable Long usuarioId,
            @RequestBody PerfilRequestDTO dto) {

        var uc = factory.removerPerfisUsuario();

        Set<String> perfisDel = dto.perfis().stream().map(Enum::name).collect(Collectors.toSet());

        uc.run(usuarioId, perfisDel);

        return ResponseEntity.noContent().build();
    }
}
