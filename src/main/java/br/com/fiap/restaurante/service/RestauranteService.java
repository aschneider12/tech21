package br.com.fiap.restaurante.service;

import br.com.fiap.restaurante.dtos.*;
import br.com.fiap.restaurante.entities.Endereco;
import br.com.fiap.restaurante.entities.Restaurante;
import br.com.fiap.restaurante.entities.Usuario;
import br.com.fiap.restaurante.exceptions.ValidationException;
import br.com.fiap.restaurante.repositories.EnderecoRepository;
import br.com.fiap.restaurante.repositories.RestauranteRepository;
import br.com.fiap.restaurante.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestauranteService {





    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = false)
    public RestauranteResponseDTO cadastrarRestaurante(RestauranteInsertDTO dto ) {

        Endereco endereco = null;
        EnderecoResponseDTO enderecoResponseDTO = null;

        if (dto.endereco() != null) {
            // A instância de Endereco é criada e salva APENAS SE dto.endereco() NÃO FOR NULL
            endereco = new Endereco();
            endereco.setRua(dto.endereco().rua());
            endereco.setNumero(dto.endereco().numero());
            endereco.setCidade(dto.endereco().cidade());
            endereco.setEstado(dto.endereco().estado());
            endereco.setCep(dto.endereco().cep());

            endereco = enderecoRepository.save(endereco);

            // 2. Preencher o EnderecoResponseDTO APENAS SE um endereço foi criado/associado
            enderecoResponseDTO = new EnderecoResponseDTO( // ATRIBUÍDO AQUI
                    endereco.getId(),
                    endereco.getRua(),
                    endereco.getNumero(),
                    endereco.getCidade(),
                    endereco.getEstado(),
                    endereco.getCep()
            );
        }

        // 3. Buscar o Usuario existente pelo ID
        //    (Mudou de dto.usuarioId() para dto.dono() - certifique-se que o DTO tem 'dono')
        Usuario usuario = usuarioRepository.findById(dto.dono())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado com o ID " + dto.dono()));

        // 4. Criar e preencher o Restaurante
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(dto.nome());
        restaurante.setTipoCozinha(dto.tipoCozinha());
        restaurante.setHorarioFuncionamento(dto.horarioFuncionamento());
        restaurante.setEndereco(endereco); // 'endereco' pode ser null aqui, o que é permitido
        restaurante.setDono(usuario); // (Mudou de setUsuario para setDono - certifique-se que a entidade tem 'dono')

        repository.save(restaurante);

        // 5. Construir o RestauranteResponseDTO
        //    'enderecoResponseDTO' agora está no escopo correto e pode ser null
        RestauranteResponseDTO restauranteResponseDTO = new RestauranteResponseDTO(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento()
                // Passa o DTO de resposta do endereço (pode ser null)
        );

        return restauranteResponseDTO;
    }

    public RestauranteResponseDTO atualizarRestaurante(Long id, RestauranteUpdateDTO dto ) {

        Restaurante restaurante = buscarRestaurantePorId(id);

        restaurante.setNome(dto.nome());
        restaurante.setTipoCozinha(dto.tipoCozinha());
        restaurante.setHorarioFuncionamento(dto.horarioFuncionamento());


        restaurante = repository.save(restaurante);

        return new RestauranteResponseDTO(restaurante);



    }

    public List<RestauranteDTO> buscarTodosRestaurantes() {

        return repository.findAll()
                .stream()
                .map(r -> new RestauranteDTO(r.getId(), r.getNome()))
                .toList();
    }


    public Restaurante buscarRestaurantePorId(Long restauranteId) {

        return repository.findById(restauranteId)
                .orElseThrow(() -> new ValidationException("Restaurante não Encontrado"));

    }



    public void deletarRestaurante(Long id) {
        if(repository.existsById(id))
            repository.deleteById(id);
        else
            throw new ValidationException("Restaurante não existe!");
    }
}
