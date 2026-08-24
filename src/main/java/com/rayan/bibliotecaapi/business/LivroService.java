package com.rayan.bibliotecaapi.business;

import com.rayan.bibliotecaapi.business.dto.LivroRequestDTO;
import com.rayan.bibliotecaapi.business.dto.LivroResponseDTO;
import com.rayan.bibliotecaapi.business.enums.StatusLivroEnum;
import com.rayan.bibliotecaapi.business.mapper.LivroMapper;
import com.rayan.bibliotecaapi.infrastructure.entity.LivroEntity;
import com.rayan.bibliotecaapi.infrastructure.exceptions.ConflictException;
import com.rayan.bibliotecaapi.infrastructure.exceptions.ResourceNotFoundException;
import com.rayan.bibliotecaapi.infrastructure.repository.LivroRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor

@Builder
public class LivroService {
    private final LivroRepository livroRepository;

    public LivroResponseDTO cadastrarLivro(LivroRequestDTO dto) {
        if(livroRepository.existsByIsbn(dto.getIsbn())){
            throw new ConflictException("ISBN já cadastrada!!");
        }
        //converte dto para entity
        LivroEntity entity = LivroMapper.paraLivroEntity(dto);

        entity.setStatus(StatusLivroEnum.DISPONIVEL);
        entity.setDataCadastro(LocalDateTime.now());
        // salva no banco entity
        LivroEntity livroSalvo = livroRepository.save(entity);
        // converte entity para dto e retorna
        return LivroMapper.paraLivroDTO(livroSalvo);
    }

    public List<LivroResponseDTO> buscarTodosLivros() {
        List<LivroEntity> entidades = livroRepository.findAll();
        List<LivroResponseDTO> entidadesDto = new ArrayList<>();

        for(int i=0;i<entidades.size();i++){
            LivroEntity entity = entidades.get(i);
            LivroResponseDTO dto = LivroMapper.paraLivroDTO(entity);
            entidadesDto.add(dto);
        }
        return entidadesDto;
    }

    public LivroResponseDTO buscarLivroId(Long id) {
        LivroEntity entity = livroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID do livro não encontrado!"));
        return LivroMapper.paraLivroDTO(entity);
    }

    public LivroResponseDTO atualizarLivro(LivroRequestDTO dto,Long id){
        LivroEntity entity = livroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID do livro não encontrado!"));

        LivroMapper.atualizarLivroMapper(entity, dto);
        LivroEntity livroAtualizado = livroRepository.save(entity);
        return LivroMapper.paraLivroDTO(livroAtualizado);
    }

    public void deletarLivro(Long id){
        LivroEntity livroEncontrado = livroRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ID do livro não encontrado! "));
        livroRepository.delete(livroEncontrado);
    }

    public LivroResponseDTO emprestarLivro(Long id){
        LivroEntity entity = livroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID do livro não encontrado!"));
        if(entity.getStatus() == StatusLivroEnum.EMPRESTADO) {
            throw new ConflictException("Livro já Emprestado!!");
        }
        entity.setStatus(StatusLivroEnum.EMPRESTADO);
        LivroEntity livroAtualizado = livroRepository.save(entity);
        return LivroMapper.paraLivroDTO(livroAtualizado);
    }
    public LivroResponseDTO devolverLivro(Long id){
        LivroEntity entity = livroRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ID do livro não encontrado!!"));
        if(entity.getStatus() == StatusLivroEnum.DISPONIVEL){
            throw new ConflictException("Livro já está disponível!!!");
        }
        entity.setStatus(StatusLivroEnum.DISPONIVEL);
        LivroEntity livroAtualizado = livroRepository.save(entity);
        return LivroMapper.paraLivroDTO(livroAtualizado);
    }

    public List<LivroResponseDTO> buscarLivrosPorStatus(StatusLivroEnum statusLivroEnum){
        List<LivroEntity> livros  = livroRepository.findAllByStatus(statusLivroEnum);
        List<LivroResponseDTO> dtos = new ArrayList<>();

        for(int i=0;i<livros .size();i++){
            LivroEntity livroEntity = livros.get(i);
            LivroResponseDTO livroDTO = LivroMapper.paraLivroDTO(livroEntity);
            dtos.add(livroDTO);
        }
        return dtos;

    }
}
