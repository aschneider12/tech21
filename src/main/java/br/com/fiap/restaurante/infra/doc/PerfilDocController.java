package br.com.fiap.restaurante.infra.doc;
import br.com.fiap.restaurante.infra.dtos.PerfilRequestDTO;
import br.com.fiap.restaurante.infra.dtos.PerfilResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Perfis de usuários", description = "Operações relacionadas ao gerenciamento do perfil de acesso dos usuários")
public interface PerfilDocController {

    @Operation(summary = "Listar perfis", description = "Listar todos os perfis vinculados ao usuário cadastrado")
    public ResponseEntity<PerfilResponseDTO> listarPerfis(Long usuarioId);

    @Operation(summary = "Cadastrar novo perfil", description = "Realiza a associação dos perfis ao usuário cadastrado")
    public ResponseEntity<Void> adicionarPerfis(Long usuarioId, PerfilRequestDTO dto);

    @Operation(summary = "Remover perfil", description = "Remove os perfis associados ao cadastro do usuário")
    public ResponseEntity<Void> removerPerfis(Long usuarioId, PerfilRequestDTO dto);
}
