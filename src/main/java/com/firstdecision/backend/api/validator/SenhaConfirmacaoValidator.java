package com.firstdecision.backend.api.validator;

import com.firstdecision.backend.domain.annotation.SenhaConfirmacao;
import com.firstdecision.backend.domain.dto.CriarUsuarioDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SenhaConfirmacaoValidator implements ConstraintValidator<SenhaConfirmacao, Object> {
    @Override
    public void initialize(SenhaConfirmacao constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object dto, ConstraintValidatorContext context) {
        try {
            String senha = (String) dto.getClass().getMethod("getSenha").invoke(dto);
            String confirmacaoSenha = (String) dto.getClass().getMethod("getConfirmacaoSenha").invoke(dto);

            if (senha == null || !senha.equals(confirmacaoSenha)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("As senhas não coincidem")
                        .addPropertyNode("confirmacaoSenha")
                        .addConstraintViolation();
                return false;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("DTO não possui métodos getSenha ou getConfirmacaoSenha", e);
        }
        return true;
    }
}
