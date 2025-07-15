package br.com.fiap.restaurante.service;

import br.com.fiap.restaurante.dtos.RestauranteDTO;
import br.com.fiap.restaurante.dtos.UsuarioResponseDTO;
import br.com.fiap.restaurante.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository repository;

    public List<RestauranteDTO> buscarTodosRestaurantes() {

        return repository.findAll()
                .stream()
                .map(r -> new RestauranteDTO(r.getId(), r.getNome()))
                .toList();
    }
}
