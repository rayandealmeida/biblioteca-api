package com.rayan.bibliotecaapi.infrastructure.repository;

import com.rayan.bibliotecaapi.infrastructure.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<LivroEntity, Long>{

    boolean existsByIsbn(String isbn);

}
