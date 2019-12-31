package com.tt.shop.repository;

import com.tt.shop.domain.Category;
import com.tt.shop.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();

    @Query
    List<Product> findAllByCategoryId(@Param("CATEGORY_ID") Long id);
}
