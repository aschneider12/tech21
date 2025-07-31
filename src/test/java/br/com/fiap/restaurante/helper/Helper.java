package br.com.fiap.restaurante.helper;

import br.com.fiap.restaurante.domain.models.Endereco;
import br.com.fiap.restaurante.domain.models.ItemCardapio;
import br.com.fiap.restaurante.domain.models.Restaurante;
import br.com.fiap.restaurante.domain.models.Usuario;
import br.com.fiap.restaurante.infra.database.entities.EnderecoEntity;
import br.com.fiap.restaurante.infra.database.entities.RestauranteEntity;
import br.com.fiap.restaurante.infra.database.entities.UsuarioEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;

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

    public static Restaurante gerarRestaurante(){
        Endereco endereco = new Endereco(1L, "Rua teste", "123", "São Paulo", "São Paulo", "00000-000");
        return new Restaurante("Restaurante teste", "Cozinha teste", "Noturno", endereco, gerarUsuario());
    }

    public static ItemCardapio gerarItemCardapio(){
        return ItemCardapio.create(
          "item teste",
          "Descrição teste",
                BigDecimal.valueOf(10.00),
          "Delivery",
          gerarRestaurante(),
          "./caminhoteste.png"
        );

    }



}
