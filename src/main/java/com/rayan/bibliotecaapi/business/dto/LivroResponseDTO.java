package com.rayan.bibliotecaapi.business.dto;

import com.rayan.bibliotecaapi.business.enums.StatusLivroEnum;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class LivroResponseDTO {
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private StatusLivroEnum status;
    private LocalDateTime dataCadastro;

}
