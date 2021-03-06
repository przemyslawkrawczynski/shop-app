package com.tt.shop.repository;

import com.tt.shop.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findAll();
    Optional<Category> getFirstByName(String name);
}
