package com.tt.shop.repository;

import com.tt.shop.domain.Category;
import com.tt.shop.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();
    List<Product> findAllByCategory(Category category);
}
