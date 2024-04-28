package com.firstdecision.backend.api.exception;


import com.firstdecision.backend.domain.dto.MensagemRetornoDTO;

public class EmailJaRegistradoException extends RuntimeException{
    public EmailJaRegistradoException(String message) {
        super(message);
    }

    public MensagemRetornoDTO toMensagemRetorno() {
        MensagemRetornoDTO mensagemRetorno = new MensagemRetornoDTO();
        mensagemRetorno.setMensagem(getMessage());
        mensagemRetorno.setStatus(false);
        return mensagemRetorno;
    }
}
