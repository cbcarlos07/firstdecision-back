package com.firstdecision.backend.domain.service;

import com.firstdecision.backend.domain.dto.*;
import com.firstdecision.backend.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    MensagemRetornoDTO salvar(CriarUsuarioDTO usuario);

    MensagemRetornoDTO atualizar(Long id, AtualizarUsuarioDTO usuario);

    MensagemRetornoDTO atualizarSenha(Long id, AtualizarSenhaDTO usuario);

    UsuarioDTO buscar(Long id);

    List<Usuario> listarTodos();

    MensagemRetornoDTO remover(Long id);
}
