package com.firstdecision.backend.domain.dto;

import lombok.Data;

@Data
public class IndexDTO {
    private String nome;
    private String versao;

    public IndexDTO(String nome, String versao) {
        this.nome = nome;
        this.versao = versao;
    }

}
