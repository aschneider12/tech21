package br.com.fiap.restaurante.application.service;

import br.com.fiap.restaurante.application.dtos.RestauranteDTO;
import br.com.fiap.restaurante.application.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Deprecated
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
