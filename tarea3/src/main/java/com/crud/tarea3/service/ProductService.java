package com.crud.tarea3.service;

import com.crud.tarea3.dto.ProductDTO;
import com.crud.tarea3.entity.Category;
import com.crud.tarea3.entity.Product;
import com.crud.tarea3.exception.ResourceNotFoundException;
import com.crud.tarea3.mapper.ProductMapper;
import com.crud.tarea3.repository.CategoryRepository;
import com.crud.tarea3.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository; // Para buscar la categoría
    private final ProductMapper productMapper; // Inyección del Mapper

    // Función de ayuda para obtener la entidad o lanzar excepción
    private Product getProductEntity(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
    }

    // Función de ayuda para obtener la categoría o lanzar excepción
    private Category getCategoryEntity(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + categoryId));
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts(String name) {
        List<Product> products;
        if (name != null && !name.isEmpty()) {
            products = productRepository.findByNameContainingIgnoreCase(name);
        } else {
            products = productRepository.findAll();
        }
        // Conversión Entidad -> DTO
        return products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDTO getProductById(Long id) {
        return productMapper.toDto(getProductEntity(id)); // Conversión Entidad -> DTO
    }

    @Transactional
    public ProductDTO createProduct(ProductDTO dto) {
        Category category = getCategoryEntity(dto.getCategoryId()); // Busca la categoría
        Product product = productMapper.toEntity(dto, category); // Conversión DTO -> Entidad
        Product createdProduct = productRepository.save(product);
        return productMapper.toDto(createdProduct); // Conversión Entidad -> DTO
    }

    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product product = getProductEntity(id); // Obtiene entidad existente
        Category newCategory = getCategoryEntity(dto.getCategoryId()); // Obtiene la nueva categoría

        // Aplica los cambios desde el DTO a la entidad
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setCategory(newCategory); // Actualiza la categoría

        Product updatedProduct = productRepository.save(product);
        return productMapper.toDto(updatedProduct); // Conversión Entidad -> DTO
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = getProductEntity(id);
        productRepository.delete(product);
    }
}