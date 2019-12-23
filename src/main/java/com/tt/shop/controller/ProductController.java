package com.tt.shop.controller;

import com.tt.shop.domain.Product;
import com.tt.shop.domain.dto.ProductDto;
import com.tt.shop.exception.ProductNotFoundException;
import com.tt.shop.mapper.ProductMapper;
import com.tt.shop.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shop/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity
                .ok()
                .body(productMapper.mapToProductDtoList(productService.getAllProducts()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) throws ProductNotFoundException {
         return ResponseEntity
                    .ok()
                    .body(productMapper.mapToProductDto(productService.getProductById(id)));
    }
}
