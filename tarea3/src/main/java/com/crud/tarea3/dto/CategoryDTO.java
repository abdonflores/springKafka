package com.crud.tarea3.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;

    @NotBlank(message = "{category.name.notblank}")
    @Size(min = 3, max = 50, message = "{category.name.size}")
    private String name;
}