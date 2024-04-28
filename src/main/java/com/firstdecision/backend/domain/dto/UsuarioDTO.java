package com.firstdecision.backend.domain.dto;

import com.firstdecision.backend.domain.model.Usuario;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public static UsuarioDTO create(Usuario usuario) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, UsuarioDTO.class);
    }
}
