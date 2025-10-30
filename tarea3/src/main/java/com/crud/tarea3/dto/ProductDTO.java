package com.crud.tarea3.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    @NotBlank(message = "{product.name.notblank}")
    @Size(min = 3, max = 100, message = "{product.name.size}")
    private String name;

    @Size(max = 500, message = "{product.description.size}")
    private String description;

    @NotNull(message = "{product.price.notnull}")
    @DecimalMin(value = "0.01", message = "{product.price.decimalmin}")
    private BigDecimal price;

    @NotNull(message = "{product.stock.notnull}")
    @Min(value = 0, message = "{product.stock.min}")
    private Integer stock;

    // ID de categoría para la relación
    @NotNull(message = "{product.categoryId.notnull}")
    private Long categoryId;

    private String categoryName; // Para mostrar el nombre en la respuesta
}