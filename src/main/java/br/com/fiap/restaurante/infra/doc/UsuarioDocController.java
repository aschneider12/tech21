package br.com.fiap.restaurante.infra.doc;

import br.com.fiap.restaurante.infra.database.entities.UsuarioEntity;
import br.com.fiap.restaurante.infra.dtos.MudarSenhaDTO;
import br.com.fiap.restaurante.infra.dtos.UsuarioInsertDTO;
import br.com.fiap.restaurante.infra.dtos.UsuarioResponseDTO;
import br.com.fiap.restaurante.infra.dtos.UsuarioUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Controller de usuários",
        description = "Operações relacionadas ao CRUD dos usuários")
public interface UsuarioDocController {

    @Operation(description = "Cadastrar um novo usuário")
    ResponseEntity<UsuarioResponseDTO> cadastrar(UsuarioInsertDTO usuarioDTO);

    @Operation(description = "Atualizar usuário existente.")
    ResponseEntity<UsuarioResponseDTO> atualizar( UsuarioUpdateDTO usuarioDTO, Long id);

    @Operation(description = "Deletar usuário.")
    ResponseEntity<String> deletar(Long id);

    @Operation(description = "Retorna uma lista contendo todos os usuários.")
    ResponseEntity<List<UsuarioResponseDTO>> buscarTodos();

    @Operation(description = "Buscar usuário por ID.")
    ResponseEntity<UsuarioResponseDTO> buscarPorId(Long id);

    @Operation(summary = "Altera a senha do usuário.")
    ResponseEntity<Void> mudarSenha(MudarSenhaDTO mudarSenhaDTO, Long id);
}