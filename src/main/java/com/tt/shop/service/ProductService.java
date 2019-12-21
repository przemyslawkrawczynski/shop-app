package com.tt.shop.service;

import com.tt.shop.domain.Product;
import com.tt.shop.exception.ProductNotFoundException;
import com.tt.shop.repository.CategoryRepository;
import com.tt.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {


    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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

    public Product getProductById(long id) throws ProductNotFoundException {
        Optional<Product> opt = productRepository.findById(id);

        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new ProductNotFoundException("Nie znaleziono produktu o podanym id: " + id);
        }
    }

    public List<Product> getAllProductsByCategoryId(long categoryId) {
        return productRepository.findAll()
                .stream()
                .filter(p -> p.getCategory().getId() == categoryId)
                .collect(Collectors.toList());
    }
}
