package com.tt.shop.service;

import com.tt.shop.domain.Category;
import com.tt.shop.exception.CategoryNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CategoryServiceTestSuits {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testAddCategory() {
        //Given
        Category category = new Category("Articles");

        //When
        long numberOfCategoryBeforeAddNew = categoryService.countAllCategory();
        categoryService.addCategory(category);
        long numberOfCategoryAfterAddNew = categoryService.countAllCategory();

        //Then
        Assert.assertEquals(numberOfCategoryBeforeAddNew, numberOfCategoryAfterAddNew - 1);
    }

    @Test
    public void testDeleteCategory() {
        //Given
        Category category = new Category("Articles");
        Category category1 = new Category("Tools");
        Category categoryToDelete = new Category("Books");

        //When
        categoryService.addCategory(category);
        categoryService.addCategory(category1);
        categoryService.addCategory(categoryToDelete);

        long numberOfCategoryBeforeDelete = categoryService.countAllCategory();
        categoryService.deleteCategory(categoryToDelete);

        long numberOfCategoryAfterDelete = categoryService.countAllCategory();

        //Then
        Assert.assertEquals(numberOfCategoryBeforeDelete, numberOfCategoryAfterDelete + 1);
    }


    @Test
    public void testCountAllCategory() {
        //Given
        Category category = new Category("Articles");
        Category category1 = new Category("Tools");
        Category category2 = new Category("Books");

        long allCategoryNumberBeforeSaveNew = categoryService.countAllCategory();

        categoryService.addCategory(category);
        categoryService.addCategory(category1);
        categoryService.addCategory(category2);

        //When
        long allCategoryNumberAfterSaveNew = categoryService.countAllCategory();

        //Then
        Assert.assertEquals(allCategoryNumberBeforeSaveNew, allCategoryNumberAfterSaveNew - 3);

    }

    @Test
    public void testGetAllCategories() {

        //Given
        Category category = new Category("Articles");
        Category category1 = new Category("Tools");
        Category category2 = new Category("Books");

        categoryService.addCategory(category);
        categoryService.addCategory(category1);
        categoryService.addCategory(category2);

        long allCategoryNumberAfterSaveNew = categoryService.countAllCategory();

        //When
        List<Category> categories = categoryService.getAllCategories();
        long result = categories.size();

        //Then
        Assert.assertTrue(categories.contains(category));
        Assert.assertTrue(categories.contains(category1));
        Assert.assertTrue(categories.contains(category2));
        Assert.assertEquals(result, allCategoryNumberAfterSaveNew);

    }

    @Test
    public void testGetCategoryByName() throws CategoryNotFoundException {

        //Given
        Category category = new Category("Articles");
        Category category1 = new Category("Tools");
        Category category2 = new Category("Books");

        categoryService.addCategory(category);
        categoryService.addCategory(category1);
        categoryService.addCategory(category2);

        //When
        Category result = categoryService.getCategoryByName(category1.getName());
        boolean isMethodThrowException = false;

        try {
            categoryService.getCategoryByName("Wrong data");
        } catch (CategoryNotFoundException ex) {
            System.out.println("Method throws exception: " + ex.getMessage());
            isMethodThrowException = true;
        }

        //Then
        Assert.assertEquals(category1.getName(), result.getName());
        Assert.assertTrue(isMethodThrowException);

    }

    @Test
    public void testGetCategoryById() throws CategoryNotFoundException {
        //Given
        Category category = new Category("Articles");
        Category category1 = new Category("Tools");
        Category category2 = new Category("Books");

        categoryService.addCategory(category);
        categoryService.addCategory(category1);
        categoryService.addCategory(category2);

        Category categoryToSearch = categoryService.getCategoryByName("Articles");
        long categoryId = categoryToSearch.getId();

        //When
        Category categoryFoundById = categoryService.getCategoryById(categoryId);

        //Then
        Assert.assertEquals(categoryToSearch, categoryFoundById);
    }
}
