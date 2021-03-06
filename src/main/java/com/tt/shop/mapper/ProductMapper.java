package com.tt.shop.mapper;

import com.tt.shop.domain.Category;
import com.tt.shop.domain.Product;
import com.tt.shop.domain.dto.responseDto.CategoryDto;
import com.tt.shop.domain.dto.responseDto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getStorageQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName()
        );
    }

    public List<ProductDto> mapToProductDtoList(List<Product> products) {
        return products.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }

    public List<CategoryDto> mapToCategoryDtoList(List<Category> categories) {
        return categories.stream()
                .map(this::mapToCategory)
                .collect(Collectors.toList());
    }

    public CategoryDto mapToCategory(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }
}
