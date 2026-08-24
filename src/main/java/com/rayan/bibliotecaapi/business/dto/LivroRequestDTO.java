package com.rayan.bibliotecaapi.business.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LivroRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 150, message = "O título deve ter no máximo 150 caracteres")
    private String titulo;

    @NotBlank(message = "O Autor é obrigatório")
    @Size(max = 150, message = "O autor deve ter no máximo 150 caracteres")
    private String autor;

    @NotBlank(message = "O ISBN é obrigatório")
    private String isbn;
}
