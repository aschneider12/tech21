package br.com.fiap.restaurante.infra.controller;

import br.com.fiap.restaurante.application.gateways.UsuarioGateway;
import br.com.fiap.restaurante.application.usecases.usuario.UseCaseAdicionarPerfisUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.UseCaseBuscarPerfisUsuario;
import br.com.fiap.restaurante.application.usecases.usuario.UseCaseRemoverPerfisUsuario;
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

@RestController
@RequestMapping("/usuario/{usuarioId}/perfil")
public class PerfilController implements PerfilDocController {

//    private static final Logger logger = LoggerFactory.getLogger(PerfilController.class);

    private final UsuarioGateway gateway;

    public PerfilController(UsuarioRepository repository) {
        gateway = UsuarioGateway.create(new UsuarioRepositoryAdapter(repository));
    }

    @Override
    @GetMapping
    public ResponseEntity<PerfilResponseDTO> listarPerfis(@PathVariable Long usuarioId) {

        var uc = UseCaseBuscarPerfisUsuario.create(gateway);

        List<String> perfis = uc.run(usuarioId);

        List<TipoUsuario> perfisEnum = perfis.stream().map(TipoUsuario::valueOf).toList();


        //  PerfilResponseDTO dto = service.listarPerfis(usuarioId);

       // List<String> perfis = domainController.listarPerfisUsuario(usuarioId);

        return ResponseEntity.ok(new PerfilResponseDTO("usuario nome", perfisEnum));
    }
    @Override
    @PostMapping
    public ResponseEntity<Void> adicionarPerfis(
            @PathVariable Long usuarioId,
            @RequestBody PerfilRequestDTO dto) {

        List<String> perfisString = dto.perfis().stream().map(Enum::name).toList();

        var uc = UseCaseAdicionarPerfisUsuario.create(gateway);
        uc.run(usuarioId, perfisString);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> removerPerfis(
            @PathVariable Long usuarioId,
            @RequestBody PerfilRequestDTO dto) {

        var uc = UseCaseRemoverPerfisUsuario.create(gateway);

        List<String> perfisDel = dto.perfis().stream().map(Enum::name).toList();

        uc.run(usuarioId, perfisDel);

        return ResponseEntity.noContent().build();
    }
}
