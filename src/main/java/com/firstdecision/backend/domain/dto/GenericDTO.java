package com.firstdecision.backend.domain.dto;

import com.firstdecision.backend.domain.model.Usuario;
import org.modelmapper.ModelMapper;

public class GenericDTO {

    public static <T> Usuario create(T usuario) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, Usuario.class);
    }
}
