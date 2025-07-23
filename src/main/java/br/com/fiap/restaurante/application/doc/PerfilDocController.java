package br.com.fiap.restaurante.application.doc;
import br.com.fiap.restaurante.application.dtos.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Perfis de usuários", description = "Operações relacionadas ao gerenciamento do perfil de acesso dos usuários")
public interface PerfilDocController {

    @Operation(summary = "Listar perfis", description = "Listar todos os perfis vinculados ao usuário cadastrado")
    public ResponseEntity<PerfilResponseDTO> listarPerfis(Long usuarioId);

    @Operation(summary = "Cadastrar novo perfil", description = "Realiza a associação dos perfis ao usuário cadastrado")
    public ResponseEntity<Void> adicionarPerfis(Long usuarioId, PerfilRequestDTO dto);

    @Operation(summary = "Remover perfil", description = "Remove os perfis associados ao cadastro do usuário")
    public ResponseEntity<Void> removerPerfis(Long usuarioId, PerfilRequestDTO dto);
}
