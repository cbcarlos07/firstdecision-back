package com.firstdecision.backend.domain.dto;

import com.firstdecision.backend.domain.annotation.SenhaConfirmacao;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@SenhaConfirmacao
public class CriarUsuarioDTO {
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "O e-mail deve ser válido")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
    private String senha;

    @NotBlank(message = "A confirmação de senha é obrigatória")
    private String confirmacaoSenha;
}
