package com.crud.tarea3.mapper;

import com.crud.tarea3.dto.ProductDTO;
import com.crud.tarea3.entity.Category;
import com.crud.tarea3.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDto(Product product) {
        if (product == null) {
            return null;
        }
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .build();
    }

    public Product toEntity(ProductDTO dto, Category category) {
        if (dto == null) {
            return null;
        }
        return Product.builder()
                .id(dto.getId()) // Se usa para PUT
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .category(category)
                .build();
    }
}