package com.firstdecision.backend.api.validator;

import com.firstdecision.backend.domain.annotation.SenhaConfirmacao;
import com.firstdecision.backend.domain.dto.CriarUsuarioDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SenhaConfirmacaoValidator implements ConstraintValidator<SenhaConfirmacao, CriarUsuarioDTO> {
    @Override
    public void initialize(SenhaConfirmacao constraintAnnotation) {
    }

    @Override
    public boolean isValid(CriarUsuarioDTO dto, ConstraintValidatorContext context) {
        if (dto.getSenha() == null || !dto.getSenha().equals(dto.getConfirmacaoSenha())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("As senhas não coincidem")
                    .addPropertyNode("confirmacaoSenha")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
