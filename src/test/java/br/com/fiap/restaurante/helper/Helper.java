package br.com.fiap.restaurante.helper;

import br.com.fiap.restaurante.domain.models.Endereco;
import br.com.fiap.restaurante.domain.models.ItemCardapio;
import br.com.fiap.restaurante.domain.models.Restaurante;
import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.infra.database.entities.*;
import br.com.fiap.restaurante.infra.dtos.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Helper {
    public static UsuarioEntity gerarUsuarioEntity(){
        EnderecoEntity endereco = new EnderecoEntity();
        endereco.setId(1L);
        endereco.setNumero("123");
        endereco.setRua("Rua teste");
        endereco.setCidade("São Paulo");
        endereco.setEstado("São Paulo");
        endereco.setCep("00000-000");


        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome("Nome teste");
        usuario.setEmail( "teste@fiap.com.br");
        usuario.setLogin("teste");
        usuario.setSenha( "1234");
        usuario.setEndereco(endereco);
        usuario.setPerfis(new HashSet<>());

        return usuario;
    }

    public static Usuario gerarUsuario(){
        Endereco endereco = new Endereco(1L, "Rua teste", "123", "São Paulo", "São Paulo", "00000-000");

        Usuario usuario = new Usuario();
        usuario.setNome("Nome teste");
        usuario.setEmail( "teste@fiap.com.br");
        usuario.setLogin("teste");
        usuario.setSenha( "Senha1234*");
        usuario.setEndereco(endereco);
        usuario.setPerfis(new HashSet<>());

        return usuario;
    }

    public static UsuarioInsertDTO gerarUsuarioInsertDTO(Usuario usuario){
        Set<TipoUsuario> perfis = usuario.getPerfis().stream()
                .map(nome -> Enum.valueOf(TipoUsuario.class, nome))
                .collect(Collectors.toSet());
        return new UsuarioInsertDTO(
          usuario.getNome(),
          usuario.getEmail(),
          usuario.getLogin(),
          usuario.getSenha(),
          perfis,
          EnderecoDTO.create(
                  usuario.getEndereco().getId(),
                  usuario.getEndereco().getRua(),
                  usuario.getEndereco().getNumero(),
                  usuario.getEndereco().getCidade(),
                  usuario.getEndereco().getEstado(),
                  usuario.getEndereco().getCep()

          )
        );
    }

    public static UsuarioUpdateDTO gerarUsuarioUpdateDTO(Usuario usuario){
        EnderecoEntity endereco = new EnderecoEntity();
        endereco.setId(1L);
        endereco.setNumero("123");
        endereco.setRua("Rua teste");
        endereco.setCidade("São Paulo");
        endereco.setEstado("São Paulo");
        endereco.setCep("00000-000");

        return new UsuarioUpdateDTO(
          usuario.getNome(),
          usuario.getEmail(),
          usuario.getLogin(),
          endereco
        );
    }

    public static UsuarioResponseDTO gerarUsuarioResponseDTO(Usuario usuario){
        Set<TipoUsuario> perfis = usuario.getPerfis().stream()
                .map(nome -> Enum.valueOf(TipoUsuario.class, nome))
                .collect(Collectors.toSet());

        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getLogin(),
            perfis,
            EnderecoDTO.create(
                    usuario.getEndereco().getId(),
                    usuario.getEndereco().getRua(),
                    usuario.getEndereco().getNumero(),
                    usuario.getEndereco().getCidade(),
                    usuario.getEndereco().getEstado(),
                    usuario.getEndereco().getCep()

            )
        );
    }


    public static Restaurante gerarRestaurante(){
        Endereco endereco = new Endereco(1L, "Rua teste", "123", "São Paulo", "São Paulo", "00000-000");
        return new Restaurante("Restaurante teste", "Cozinha teste", "Noturno", endereco, gerarUsuario());
    }

    public static RestauranteDTO gerarRestauranteDTO(Restaurante restaurante){
        return new RestauranteDTO(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento(),
                restaurante.getDono().getId(),
                EnderecoDTO.create(
                        restaurante.getEndereco().getId(),
                        restaurante.getEndereco().getRua(),
                        restaurante.getEndereco().getNumero(),
                        restaurante.getEndereco().getCidade(),
                        restaurante.getEndereco().getEstado(),
                        restaurante.getEndereco().getCep()
                )
        );
    }

    public static ItemCardapio gerarItemCardapio(){
        return ItemCardapio.create(
          "item teste",
          "Descrição teste",
                BigDecimal.valueOf(10.00),
          "DELIVERY",
          gerarRestaurante(),
          "./caminhoteste.png"
        );
    }

    public static ItemRequestDTO gerarItemRequestDTO(ItemCardapio itemCardapio){
        return new ItemRequestDTO(
                itemCardapio.getNome(),
                itemCardapio.getDescricao(),
                itemCardapio.getPreco(),
                TipoVenda.valueOf(itemCardapio.getTipoVenda()),
                itemCardapio.getPathFoto()
        );
    }





}
