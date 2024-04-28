package com.firstdecision.backend.domain.dto;

import com.firstdecision.backend.domain.annotation.SenhaConfirmacao;
import com.firstdecision.backend.domain.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
@SenhaConfirmacao
public class AtualizarSenhaDTO {
    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
    private String senha;

    @NotBlank(message = "A confirmação de senha é obrigatória")
    private String confirmacaoSenha;


}
