package com.crud.tarea3.mapper;

import com.crud.tarea3.dto.CategoryDTO;
import com.crud.tarea3.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDTO toDto(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public Category toEntity(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }
        return Category.builder()
                .id(dto.getId()) // Se usa para PUT
                .name(dto.getName())
                .build();
    }
}