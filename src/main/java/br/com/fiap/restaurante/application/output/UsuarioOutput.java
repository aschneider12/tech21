package br.com.fiap.restaurante.application.output;

import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.infra.dtos.EnderecoDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record UsuarioOutput(

        Long id,
        String nome,
        String email,
        String login,
//        String senha,
        LocalDateTime dataUltimaAlteracao,
        Set<String> perfis,
        EnderecoOutput endereco
) {

    public static UsuarioOutput create(Long id,
                                       String nome,
                                       String email,
                                       String login,
//                                       String senha,
                                       LocalDateTime dataUltimaAlteracao,
                                       Set<String> perfis,
                                       EnderecoOutput endereco) {

        return new UsuarioOutput(id, nome, email, login, dataUltimaAlteracao, perfis, endereco);
    }

    public static UsuarioOutput fromDomain(Usuario u) {
        if (u != null)
            return new UsuarioOutput(u.getId(), u.getNome(), u.getEmail(), u.getLogin(), // u.getSenha(),
                    u.getDataUltimaAlteracao(), u.getPerfis(),
                    EnderecoOutput.fromDomain(u.getEndereco()));
        return null;
    }

    public static List<UsuarioOutput> fromDomain(List<Usuario> us) {
        if (us != null && !us.isEmpty())
            return us.stream().map(UsuarioOutput::fromDomain).toList();

        return List.of();
    }
}
