package com.rayan.bibliotecaapi.infrastructure.repository;

import com.rayan.bibliotecaapi.business.dto.LivroResponseDTO;
import com.rayan.bibliotecaapi.business.enums.StatusLivroEnum;
import com.rayan.bibliotecaapi.infrastructure.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<LivroEntity, Long>{

    boolean existsByIsbn(String isbn);
    List<LivroEntity> findAllByStatus(StatusLivroEnum status);

}
