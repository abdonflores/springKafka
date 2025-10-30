package com.crud.tarea3.service;

import com.crud.tarea3.dto.CategoryDTO;
import com.crud.tarea3.dto.ProductDTO;
import com.crud.tarea3.entity.Category;
import com.crud.tarea3.exception.DataConflictException;
import com.crud.tarea3.exception.ResourceNotFoundException;
import com.crud.tarea3.mapper.CategoryMapper;
import com.crud.tarea3.mapper.ProductMapper;
import com.crud.tarea3.repository.CategoryRepository;
import com.crud.tarea3.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper; // Necesario para el endpoint de productos

    // Función de ayuda para obtener la entidad o lanzar excepción
    private Category getCategoryEntity(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDTO getCategoryById(Long id) {
        return categoryMapper.toDto(getCategoryEntity(id));
    }

    @Transactional
    public CategoryDTO createCategory(CategoryDTO dto) {
        // Validación de unicidad
        categoryRepository.findByNameIgnoreCase(dto.getName()).ifPresent(category -> {
            throw new DataConflictException("El nombre de la categoría ya existe: " + dto.getName());
        });

        Category category = categoryMapper.toEntity(dto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    @Transactional
    public CategoryDTO updateCategory(Long id, CategoryDTO dto) {
        Category category = getCategoryEntity(id);

        // Validación de unicidad, excluyendo la categoría actual
        Optional<Category> existingCategory = categoryRepository.findByNameIgnoreCase(dto.getName());
        if (existingCategory.isPresent() && !existingCategory.get().getId().equals(id)) {
            throw new DataConflictException("Ya existe otra categoría con el nombre: " + dto.getName());
        }

        category.setName(dto.getName());
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(updatedCategory);
    }

    @Transactional
    public void deleteCategory(Long id) {
        Category category = getCategoryEntity(id);

        // Prevención de eliminación: Verifica si tiene productos asociados
        if (productRepository.countByCategoryId(id) > 0) { // Se asume que CategoryRepository es mejor para esta cuenta.
            throw new DataConflictException("No se puede eliminar la categoría con id: " + id + " porque tiene productos asociados.");
        }

        categoryRepository.delete(category);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getProductsByCategoryId(Long categoryId) {
        // Verifica que la categoría exista
        getCategoryEntity(categoryId);

        return productRepository.findByCategoryId(categoryId).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}