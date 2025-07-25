package br.com.fiap.restaurante.core.usecases.restaurante;

import br.com.fiap.restaurante.core.domain.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.restaurante.core.domain.models.Restaurante;

import java.util.List;

public class UseCaseBuscarTodosRestaurantes {

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
