package com.firstdecision.backend.domain.service;
import com.firstdecision.backend.domain.dto.CriarUsuarioDTO;
import com.firstdecision.backend.domain.repository.UsuarioRepository;
import jakarta.annotation.security.RunAs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

@RunAs("admin")
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSalvarUsuario() {
        // Configuração do mock
        when(repository.existsByEmail(anyString())).thenReturn(false);

        // Criar DTO
        CriarUsuarioDTO dto = new CriarUsuarioDTO();
        dto.setNome("Teste");
        dto.setEmail("teste@example.com");
        dto.setSenha("senha123");
        dto.setConfirmacaoSenha("senha123");

        // Chamar o método salvar do serviço
        service.salvar(dto);

        // Verificar se o método save do repository foi chamado uma vez
        verify(repository, times(1)).save(any());
    }
}
