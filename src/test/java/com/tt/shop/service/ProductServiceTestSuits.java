package com.tt.shop.service;

import com.tt.shop.domain.Category;
import com.tt.shop.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTestSuits {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testAddProduct() {

        //Given
        Category category = new Category("Tools");
        Product p1 = new Product("Hammer", "Universal hammer", 15, new BigDecimal(35.99), category);

        long productListSizeBeforeAddProduct = productService.countAllProducts();

        //When
        categoryService.addCategory(category);
        productService.addProduct(p1);

        long productListSizeAfterAddProduct = productService.countAllProducts();

        //Then
        Assert.assertEquals(productListSizeBeforeAddProduct + 1, productListSizeAfterAddProduct);

        //Clear
        categoryService.deleteCategory(category);
        productService.deleteProduct(p1);
    }

    @Test
    public void testDeleteProduct() {

        //Given
        Category category = new Category("Tools");
        Product p1 = new Product("Hammer", "Universal hammer", 15, new BigDecimal(35.99), category);
        Product p2 = new Product("Handsaw", "Handsow using to cut wood", 15, new BigDecimal(15.99), category);
        Product p3 = new Product("Drill", "Home usage drill", 15, new BigDecimal(159.99), category);

        categoryService.addCategory(category);
        productService.addProduct(p1);
        productService.addProduct(p2);
        productService.addProduct(p3);
        long productListSizeBeforeAddProduct = productService.countAllProducts();

        //When
        productService.deleteProduct(p2);

        Optional<Product> product = productService.getAllProducts()
                .stream()
                .filter(p -> p.getId() == p2.getId())
                .findAny();

        boolean isExist = product.isPresent();
        long productListSizeAfterAddProduct = productService.countAllProducts();

        //Clear
        categoryService.deleteCategory(category);
        productService.deleteProduct(p1);
        productService.deleteProduct(p3);

        //Then
        Assert.assertEquals(productListSizeBeforeAddProduct - 1, productListSizeAfterAddProduct);
        Assert.assertFalse(isExist);

    }



}
