package br.com.fiap.restaurante.core.gateways;

import br.com.fiap.restaurante.core.domain.entities.Restaurante;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteCadastroDTO;
import br.com.fiap.restaurante.core.dtos.restaurante.RestauranteRetornoDTO;
import br.com.fiap.restaurante.core.interfaces.IDataStorageRestaurante;
import br.com.fiap.restaurante.core.interfaces.IRestauranteGateway;

public class RestauranteGateway implements IRestauranteGateway {

    private final IDataStorageRestaurante dataSource;

    private RestauranteGateway(IDataStorageRestaurante iDataStorageRestaurante) {
        this.dataSource = iDataStorageRestaurante;
    }

    public static RestauranteGateway create(IDataStorageRestaurante iDataStorageRestaurante) {
        return new RestauranteGateway(iDataStorageRestaurante);
    }

    @Override
    public Restaurante cadastrar(Restaurante restaurante) {

        RestauranteCadastroDTO dto = RestauranteCadastroDTO.fromEntity(restaurante);

        final RestauranteRetornoDTO rc = this.dataSource.cadastrar(dto);

        return Restaurante.create(rc.id(), rc.nome(), rc.tipoCozinha(), rc.horarioFuncionamento());
    }

    @Override
    public Restaurante buscarRestaurantePorNome(String nomeRestaurante) {
        RestauranteRetornoDTO retornoDTO = dataSource.buscarRestaurantePorNome(nomeRestaurante);

//        isso é redundante, pq esta aqui? se o gateway nao e responsavel por isso
//        if (retornoDTO == null) {
//           throw new EstudanteNaoEncontradoException("Estudante com nome " + nome + " não encontrado");

        return Restaurante.create(retornoDTO.id(), retornoDTO.nome(), retornoDTO.tipoCozinha(),
          retornoDTO.horarioFuncionamento());
    }

    @Override
    public Restaurante buscarRestaurantePorIdentificador(Long id) {
        return null;
    }


}
