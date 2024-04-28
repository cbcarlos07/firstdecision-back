package com.firstdecision.backend.api.controller;

import com.firstdecision.backend.api.exception.EmailJaRegistradoException;
import com.firstdecision.backend.domain.dto.*;
import com.firstdecision.backend.domain.model.Usuario;
import com.firstdecision.backend.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity salvar(@RequestBody @Valid CriarUsuarioDTO dto){

            MensagemRetornoDTO msg = service.salvar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(msg);

    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody AtualizarUsuarioDTO dto){
        MensagemRetornoDTO usuario =  service.atualizar(id, dto);
        return usuario != null ?
                    ResponseEntity.ok(usuario) :
                    ResponseEntity.notFound().build();
    }

    @PutMapping("/senha/{id}")
    public ResponseEntity atualizarSenha(@PathVariable("id") Long id, @RequestBody AtualizarSenhaDTO dto){
        MensagemRetornoDTO usuario = service.atualizarSenha(id, dto);
        return usuario != null ?
                 ResponseEntity.ok( usuario ) :
                 ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable("id") Long id){
        UsuarioDTO usuarioDTO = service.buscar(id);
        return ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping
    public ResponseEntity listarTodos(){
        return ResponseEntity.ok( service.listarTodos() );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable("id") Long id){
        return ResponseEntity.ok( service.remover( id ) );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
