package  br.com.fiap.restaurante.core.domain.usecases.restaurante;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;
import br.com.fiap.restaurante.core.interfaces.gateway.IRestauranteGateway;

import java.util.List;

public class UseCaseBuscarTodosRestaurantes {

    //Esse código de instanciar o gateway ja se tornou repetitivo, poderia
    //ser melhorado para evitar esse retrabalho
    private final IRestauranteGateway gateway;

    private UseCaseBuscarTodosRestaurantes(IRestauranteGateway gateway) {
        this.gateway = gateway;
    }

    public static UseCaseBuscarTodosRestaurantes create(IRestauranteGateway gateway) {
        return new UseCaseBuscarTodosRestaurantes(gateway);
    }

    public List<Restaurante> run() {

        //aqui o use case não sabe para onde o gateway vai enviar a busca, por isso ele trata como a entidade concreta
        // o gateway pode enviar para um DB, um json, um arquivo, ou qualquer lugar

        List<Restaurante> restaurantes = gateway.buscarTodosRestaurantes();

        return restaurantes;
    }
}
