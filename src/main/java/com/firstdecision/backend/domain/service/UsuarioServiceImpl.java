package com.firstdecision.backend.domain.service;

import com.firstdecision.backend.api.exception.EmailJaRegistradoException;
import com.firstdecision.backend.api.exception.ObjectNotFoundException;
import com.firstdecision.backend.domain.dto.*;
import com.firstdecision.backend.domain.model.Usuario;
import com.firstdecision.backend.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository rep;

    @Override
    public Usuario salvar(CriarUsuarioDTO dto) {
        if (rep.existsByEmail(dto.getEmail())) {
            throw new EmailJaRegistradoException("Este e-mail já está registrado");
        }
        dto.setSenha(encodePassword( dto.getSenha() ));
        Usuario usuario = GenericDTO.create( dto );
        return rep.save(usuario);
    }

    private String encodePassword(String password) {
        try {
            // Cria uma instância do MessageDigest para o algoritmo MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Atualiza o digest com a senha fornecida em bytes
            md.update(password.getBytes());

            // Converte o digest em uma matriz de bytes
            byte[] digest = md.digest();

            // Converte a matriz de bytes em uma representação hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }

            // Retorna a representação hexadecimal do hash MD5
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Trate qualquer exceção de algoritmo não encontrado aqui
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Usuario atualizar(Long id, AtualizarUsuarioDTO dto) {
        Assert.notNull(id,"Não foi possível atualizar o registro");
        Optional<Usuario> optional = rep.findById(id);
        if( optional.isPresent() ){
            Usuario db = optional.get();
            db.setNome( dto.getNome() );
            db.setEmail( dto.getEmail() );
            rep.save( db );
            return  GenericDTO.create( db );
        }else{
            return null;
        }
    }

    @Override
    public Usuario atualizarSenha(Long id, AtualizarSenhaDTO dto) {
        Assert.notNull(id,"Não foi possível atualizar o registro");
        Optional<Usuario> optional = rep.findById(id);
        if( optional.isPresent() ){
            Usuario db = optional.get();
            db.setSenha( encodePassword( dto.getSenha() ) );
            rep.save( db );
            return  GenericDTO.create( db );
        }else{
            return null;
        }
    }

    @Override
    public UsuarioDTO buscar(Long id) {
        Optional<Usuario> usuario = rep.findById(id);
        return usuario.map(UsuarioDTO::create).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    @Override
    public List<Usuario> listarTodos() {
        return rep.findAllByOrderByNomeAsc();
    }

    @Override
    public MensagemRetorno remover(Long id) {
        rep.deleteById(id);
        Optional<Usuario> usuario = rep.findById(id);
        MensagemRetorno msg = new MensagemRetorno();
        if(usuario.isEmpty() ){
            msg.setMensagem("Usuário removido com sucesso!");
            msg.setStatus(true);
        }else{
            msg.setMensagem("Usuário não encontrado!");
            msg.setStatus(false);
        }
        return msg;
    }


}
