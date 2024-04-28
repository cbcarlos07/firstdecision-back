package com.firstdecision.backend.domain.service;

import com.firstdecision.backend.domain.dto.*;
import com.firstdecision.backend.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario salvar(CriarUsuarioDTO usuario);

    Usuario atualizar(Long id, AtualizarUsuarioDTO usuario);

    Usuario atualizarSenha(Long id, AtualizarSenhaDTO usuario);

    UsuarioDTO buscar(Long id);

    List<Usuario> listarTodos();

    MensagemRetorno remover(Long id);
}
