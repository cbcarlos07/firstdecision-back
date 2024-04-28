package com.firstdecision.backend.api.exception;


import com.firstdecision.backend.domain.dto.MensagemRetornoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailJaRegistradoException.class)
    public ResponseEntity<MensagemRetornoDTO> handleEmailJaRegistradoException(EmailJaRegistradoException e) {
        MensagemRetornoDTO mensagemRetorno = e.toMensagemRetorno();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensagemRetorno);
    }
}
