package com.rayan.bibliotecaapi.infrastructure.entity;

import com.rayan.bibliotecaapi.business.enums.StatusLivroEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "livros")
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", length = 150)
    private String titulo;
    @Column(name = "autor", length = 150)
    private String autor;
    @Column(name = "isbn", length = 150)
    private String isbn;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusLivroEnum status;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;
}
