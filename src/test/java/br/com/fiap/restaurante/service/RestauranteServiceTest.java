package br.com.fiap.restaurante.service;
//
//import br.com.fiap.restaurante.dtos.EnderecoRequestDTO;
//import br.com.fiap.restaurante.dtos.RestauranteInsertDTO;
//import br.com.fiap.restaurante.dtos.RestauranteResponseDTO;
//import br.com.fiap.restaurante.entities.Endereco;
//import br.com.fiap.restaurante.entities.Restaurante;
//import br.com.fiap.restaurante.entities.Usuario;
//import br.com.fiap.restaurante.repositories.EnderecoRepository;
//import br.com.fiap.restaurante.repositories.RestauranteRepository;
//import br.com.fiap.restaurante.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestauranteServiceTest {

//    @Mock
//    private RestauranteRepository repository;
//
//    @Mock
//    private EnderecoRepository enderecoRepository;
//
//    @Mock
//    private UsuarioRepository usuarioRepository;
//
//    @InjectMocks
//    private RestauranteService restauranteService;
//
//    private Usuario usuario;
//    private RestauranteInsertDTO restauranteInsertDTOComEndereco;
//    private RestauranteInsertDTO restauranteInsertDTOSemEndereco;
//    private Restaurante restauranteMock;
//    private Endereco enderecoMock; // Mock para o objeto Endereco
//
//    @BeforeEach
//    void setUp() {
//        // --- 1. Configuração do Usuário ---
//        usuario = new Usuario();
//        usuario.setId(1L);
//        usuario.setNome("Natan");
//        usuario.setEmail("natan@email.com");
//        usuario.setLogin("natanSC1");
//        usuario.setSenha("NatanSC1@");
//        usuario.setDataUltimaAlteracao(LocalDateTime.now());
//        usuario.setPerfis("DONO");
//
//        // --- 2. Configuração do Endereço Mock (o que será retornado pelo enderecoRepository.save) ---
//        enderecoMock = new Endereco();
//        enderecoMock.setId(200L); // ID simulado para o endereço salvo
//        enderecoMock.setRua("Rua Fiap");
//        enderecoMock.setNumero("123");
//        enderecoMock.setCidade("São Paulo");
//        enderecoMock.setEstado("SP");
//        enderecoMock.setCep("01000-000");
//
//
//        // --- 3. Instância do Endereco para o DTO (o que será passado no RestauranteRequestDTO) ---
//        // Crie a instância de Endereco FORA do construtor do DTO
//        EnderecoRequestDTO enderecoParaDTO = new EnderecoRequestDTO("1","");
//        enderecoParaDTO.setRua("Rua Fiap");
//        enderecoParaDTO.setNumero("123");
//        enderecoParaDTO.setCidade("São Paulo"); // Mudado para "São Paulo" para corresponder ao enderecoMock.setCidade
//        enderecoParaDTO.setEstado("SP");
//        enderecoParaDTO.setCep("01000-000"); // Mudado para corresponder ao enderecoMock.setCep
//
//        EnderecoRequestDTO requestDTO;
//
//        // --- 4. Configuração dos DTOs de Inserção de Restaurante ---
//        // Com endereço - Agora passamos a instância 'enderecoParaDTO' diretamente
//        restauranteInsertDTOComEndereco = new RestauranteInsertDTO(
//                "McDonalds",
//                "FastFood",
//                "08:00-19:00",
//                usuario.getId(),
//                enderecoParaDTO // Passando a instância de Endereco aqui
//        );
//
//        // Sem endereço
//        restauranteInsertDTOSemEndereco = new RestauranteInsertDTO(
//                "Burger King",
//                "FastFood",
//                "09:00-20:00",
//                usuario.getId(),
//                null // Endereço nulo
//        );
//
//        // --- 5. Configuração do Restaurante que seria retornado pelo save do RestauranteRepository ---
//        restauranteMock = new Restaurante();
//        restauranteMock.setId(100L); // Simula um ID gerado pelo banco
//        restauranteMock.setNome(restauranteInsertDTOComEndereco.nome());
//        restauranteMock.setTipoCozinha(restauranteInsertDTOComEndereco.tipoCozinha());
//        restauranteMock.setHorarioFuncionamento(restauranteInsertDTOComEndereco.horarioFuncionamento());
//        restauranteMock.setEndereco(enderecoMock); // Associa o endereço mockado (o que o save retornaria)
//        restauranteMock.setDono(usuario);
//
//
//        // --- Configuração dos Comportamentos dos Mocks ---
//
//        // Mock para usuarioRepository.findById
//        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
//
//        // Mock para enderecoRepository.save
//        // Quando enderecoRepository.save for chamado com qualquer Endereco, ele deve retornar o enderecoMock.
//        when(enderecoRepository.save(any(Endereco.class))).thenReturn(enderecoMock);
//
//        // Mock para restauranteRepository.save
//        // Quando repository.save for chamado com qualquer Restaurante, ele deve retornar o restauranteMock.
//        when(repository.save(any(Restaurante.class))).thenReturn(restauranteMock);
//
//        // Para o testFindAll, quando findById for chamado no RestauranteRepository, retorne o Optional com o restauranteMock
//        when(repository.findById(anyLong())).thenReturn(Optional.of(restauranteMock));
//    }
//
//    // ... Seus métodos de teste permanecem os mesmos ...
//
//    @Test
//    public void cadastrarRestaurante_ComEndereco_DeveRetornarRestauranteSalvo() {
//        // Ação: Chamar o método que queremos testar no nosso service com endereço
//        RestauranteResponseDTO responseDTO = restauranteService.cadastrarRestaurante(restauranteInsertDTOComEndereco);
//
//        // Verificações:
//        // 1. Verifica se o usuarioRepository.findById foi chamado para buscar o dono
//        verify(usuarioRepository, times(1)).findById(usuario.getId());
//        // 2. Verifica se o enderecoRepository.save foi chamado
//        verify(enderecoRepository, times(1)).save(any(Endereco.class));
//        // 3. Verifica se o restauranteRepository.save foi chamado
//        verify(repository, times(1)).save(any(Restaurante.class));
//
//        // 4. Asserções sobre o DTO retornado
//        assertNotNull(responseDTO);
//        assertEquals("McDonalds", responseDTO.nome());
//        assertEquals("FastFood", responseDTO.tipoCozinha());
//        assertNotNull(responseDTO.endereco()); // Verifica se o endereço foi incluído na resposta
//        assertEquals(enderecoMock.getId(), responseDTO.endereco().id());
//        assertEquals(restauranteMock.getId(), responseDTO.id()); // Verifica se o ID do restaurante retornado é o mockado
//    }
//
//    @Test
//    public void cadastrarRestaurante_SemEndereco_DeveRetornarRestauranteSalvo() {
//        // Ação: Chamar o método que queremos testar no nosso service sem endereço
//        RestauranteResponseDTO responseDTO = restauranteService.cadastrarRestaurante(restauranteInsertDTOSemEndereco);
//
//        // Verificações:
//        verify(usuarioRepository, times(1)).findById(usuario.getId());
//        // Verifica que enderecoRepository.save NÃO foi chamado, pois o endereço é nulo
//        verify(enderecoRepository, never()).save(any(Endereco.class));
//        verify(repository, times(1)).save(any(Restaurante.class));
//
//        // Asserções:
//        assertNotNull(responseDTO);
//        assertEquals("Burger King", responseDTO.nome());
//        assertEquals("FastFood", responseDTO.tipoCozinha());
//        assertNull(responseDTO.endereco()); // Deve ser nulo, pois não passamos endereço
//        assertEquals(restauranteMock.getId(), responseDTO.id()); // O ID ainda deve vir do restauranteMock configurado
//    }
//
//
//    @Test
//    public void testFindAll_shouldReturnRestauranteById() {
//        // Ação: Chamar o método de busca
//        Restaurante foundRestaurante = restauranteService.buscarRestaurantePorId(restauranteMock.getId());
//
//        // Verificações:
//        // Verifica se o repository.findById foi chamado com o ID do restaurante
//        verify(repository, times(1)).findById(restauranteMock.getId());
//
//        // Asserções:
//        assertNotNull(foundRestaurante);
//        assertEquals(restauranteMock.getId(), foundRestaurante.getId());
//        assertEquals(restauranteMock.getNome(), foundRestaurante.getNome());
//        assertEquals(restauranteMock.getTipoCozinha(), foundRestaurante.getTipoCozinha());
//        // Adicione mais asserções conforme necessário
//    }
}