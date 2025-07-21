package  br.com.fiap.restaurante.core.domain.usecases.restaurante;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;
import br.com.fiap.restaurante.core.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.restaurante.core.interfaces.IRestauranteGateway;

import java.util.List;

public class UseCaseBuscarTodosRestaurantes {

    //Esse código de instanciar o gateway ja se tornou repetitivo, poderia
    //ser melhorado para evitar esse retrabalho
    private final IRestauranteGateway gateway;

    /* Metodo internalizado, forçar o uso do create */
    private UseCaseBuscarTodosRestaurantes(IRestauranteGateway gateway) {
        this.gateway = gateway;
    }

    /* poderia ser utilizado direto o construtor forçando o param gateway? */
    public static UseCaseBuscarTodosRestaurantes create(IRestauranteGateway gateway) {
        return new UseCaseBuscarTodosRestaurantes(gateway);
    }

    public List<Restaurante> run() {

        List<Restaurante> restaurantes = gateway.buscarTodosRestaurantes();
        return restaurantes;
    }
}
