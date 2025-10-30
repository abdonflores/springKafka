package com.crud.tarea3.repository;

import com.crud.tarea3.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Consulta para verificar duplicados por nombre
    Optional<Category> findByNameIgnoreCase(String name);

    // Consulta para contar productos en la categoría (para la prevención de eliminación)
    long countProductsById(Long categoryId);
}