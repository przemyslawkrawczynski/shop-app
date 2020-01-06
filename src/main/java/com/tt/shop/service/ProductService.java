package com.tt.shop.service;

import com.tt.shop.domain.Product;
import com.tt.shop.domain.dto.responseDto.CategoryDto;
import com.tt.shop.domain.dto.responseDto.ProductDto;
import com.tt.shop.exception.ProductNotFoundException;
import com.tt.shop.mapper.ProductMapper;
import com.tt.shop.repository.CategoryRepository;
import com.tt.shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    public long countAllProducts() {
        return productRepository.count();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<ProductDto> getAllProductsDto() {
        return productMapper.mapToProductDtoList(getAllProducts());
    }

    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> opt = productRepository.findById(id);
        return opt.orElseThrow(() -> new ProductNotFoundException("Nie znaleziono produktu o podanym id: " + id));
    }

    public ProductDto getProductDtoById(Long id) throws ProductNotFoundException {
        return productMapper.mapToProductDto(getProductById(id));
    }

    public List<CategoryDto> getAllCategoryList() {
        return productMapper.mapToCategoryDtoList(categoryRepository.findAll());
    }

    public List<ProductDto> getAllProductsByCategoryId(long categoryId) {
        return productMapper.mapToProductDtoList(productRepository.findAllByCategoryId(categoryId));
    }
}
