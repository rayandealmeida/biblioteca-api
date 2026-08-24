package com.rayan.bibliotecaapi.controller;

import com.rayan.bibliotecaapi.business.LivroService;
import com.rayan.bibliotecaapi.business.dto.LivroRequestDTO;
import com.rayan.bibliotecaapi.business.dto.LivroResponseDTO;
import com.rayan.bibliotecaapi.infrastructure.entity.LivroEntity;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class LivroController {
    private final LivroService livroService;

    @PostMapping("/livro")
    public ResponseEntity<LivroResponseDTO> cadastrarLivro (@Valid @RequestBody LivroRequestDTO dto){
        return ResponseEntity.status(201).body(livroService.cadastrarLivro(dto));
    }

    @GetMapping("/livros")
    public ResponseEntity<List<LivroResponseDTO>> buscarTodosLivros(){
        return ResponseEntity.ok(livroService.buscarTodosLivros());
    }

    @GetMapping("/livro")
    public ResponseEntity<LivroResponseDTO> buscarLivroId(@RequestParam Long id){
        return ResponseEntity.ok(livroService.buscarLivroId(id));
    }

    @PutMapping("/livro")
    public ResponseEntity<LivroResponseDTO> updateLivro(@RequestParam Long id,
                                                   @Valid @RequestBody LivroRequestDTO dto){
        return ResponseEntity.ok(livroService.atualizarLivro(dto, id));
    }

    @DeleteMapping("/livro/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id){
        livroService.deletarLivro(id);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/livro/{id}/emprestar")
    public  ResponseEntity<LivroResponseDTO> emprestarLivro(@PathVariable Long id){
        return ResponseEntity.ok(livroService.emprestarLivro(id));
    }

    @PatchMapping("/livro/{id}/devolver")
    public ResponseEntity<LivroResponseDTO> devolverLivro(@PathVariable Long id){
        return ResponseEntity.ok(livroService.devolverLivro(id));
    }
}
