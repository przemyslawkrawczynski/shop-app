package com.tt.shop.controller;

import com.tt.shop.domain.Category;
import com.tt.shop.domain.Product;
import com.tt.shop.domain.dto.responseDto.CategoryDto;
import com.tt.shop.domain.dto.responseDto.ProductDto;
import com.tt.shop.exception.ProductNotFoundException;
import com.tt.shop.mapper.ProductMapper;
import com.tt.shop.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity
                .ok()
                .body(productService.getAllProductsDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductDtoById(@PathVariable Long id) throws ProductNotFoundException {
        return ResponseEntity
                .ok()
                .body(productService.getProductDtoById(id));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategoriesList() {
        return ResponseEntity
                .ok()
                .body(productService.getAllCategoryList());
    }

    @GetMapping("categories/{id}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryId(@PathVariable Long id) {
        return ResponseEntity
                .ok()
                .body(productService.getAllProductsByCategoryId(id));
    }
}
