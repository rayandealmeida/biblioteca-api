package com.rayan.bibliotecaapi.business.mapper;

import com.rayan.bibliotecaapi.business.dto.LivroRequestDTO;
import com.rayan.bibliotecaapi.business.dto.LivroResponseDTO;
import com.rayan.bibliotecaapi.infrastructure.entity.LivroEntity;


/*
LivroRequestDTO → LivroEntity
LivroEntity → LivroResponseDTO
 */
public class LivroMapper {

    public static LivroEntity paraLivroEntity(LivroRequestDTO dto) {
        return LivroEntity.builder()
                .titulo(dto.getTitulo())
                .autor(dto.getAutor())
                .isbn(dto.getIsbn())
                .build();
    }

    public static LivroResponseDTO paraLivroDTO(LivroEntity entity) {
        return LivroResponseDTO.builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .autor(entity.getAutor())
                .isbn(entity.getIsbn())
                .status(entity.getStatus())
                .dataCadastro(entity.getDataCadastro())
                .build();
    }

    public static LivroEntity atualizarLivroMapper(LivroEntity entity, LivroRequestDTO livroRequestDTO){
        entity.setTitulo(livroRequestDTO.getTitulo());
        entity.setAutor(livroRequestDTO.getAutor());
        entity.setIsbn(livroRequestDTO.getIsbn());
        return entity;
    }
}
