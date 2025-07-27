package br.com.fiap.restaurante.application.output;

import br.com.fiap.restaurante.domain.models.Endereco;
import br.com.fiap.restaurante.domain.models.ItemCardapio;
import br.com.fiap.restaurante.domain.models.Restaurante;
import br.com.fiap.restaurante.domain.models.Usuario;

import java.util.List;

public record RestauranteOutput(

     Long id,
     String nome,
     String tipoCozinha,
     String horarioFuncionamento,
     EnderecoOutput endereco,
     UsuarioOutput dono,
     List<ItemCardapioOutput> itensCardapio) {

    public static RestauranteOutput fromDomain(Restaurante r) {

        return new RestauranteOutput (r.getId(), r.getNome(), r.getTipoCozinha(), r.getHorarioFuncionamento(),
               EnderecoOutput.fromDomain(r.getEndereco()),
               UsuarioOutput.fromDomain(r.getDono()),
               ItemCardapioOutput.fromDomain(r.getItensCardapio()));
    }

    public static List<RestauranteOutput> fromDomain(List<Restaurante> restaurantes) {
        return restaurantes.stream().map(RestauranteOutput::fromDomain).toList();
    }

    public static RestauranteOutput create(
            Long id,
            String nome,
            String tipoCozinha,
            String horarioFuncionamento,
            EnderecoOutput endereco,
            UsuarioOutput dono,
            List<ItemCardapioOutput> itensCardapio

    ){

        return new RestauranteOutput(id, nome, tipoCozinha, horarioFuncionamento,
                endereco, dono, itensCardapio);
    }

    public static RestauranteOutput fromDomainNoRelations(Restaurante restaurante) {
        return new RestauranteOutput(restaurante.getId(), null,
                null, null, null, null, null);
    }
}
