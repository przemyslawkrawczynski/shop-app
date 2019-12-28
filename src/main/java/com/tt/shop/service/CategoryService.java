package com.tt.shop.service;

import com.tt.shop.domain.Category;
import com.tt.shop.exception.CategoryNotFoundException;
import com.tt.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    public long countAllCategory() {
        return categoryRepository.count();
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryByName(String name) throws CategoryNotFoundException {
        Optional<Category> opt = categoryRepository.getFirstByName(name);
        return opt.orElseThrow(() ->
                new CategoryNotFoundException("Nie znaleziono kateogrii o podanej nazwie: " + name)
        );
    }

    public Category getCategoryById(long id) throws CategoryNotFoundException {
        Optional<Category> opt = categoryRepository.findById(id);
        return opt.orElseThrow(() ->
            new CategoryNotFoundException("Nie znaleziono kategorii o podanym id: " + id)
        );
    }

}
