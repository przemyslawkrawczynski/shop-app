package com.tt.shop;

import com.tt.shop.domain.Category;
import com.tt.shop.domain.Product;
import com.tt.shop.service.CategoryService;
import com.tt.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OnStartClassMain implements CommandLineRunner {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @Override
    public void run(String... args) throws Exception {

        if (categoryService.countAllCategory() == 0 && productService.countAllProducts() == 0) {

            Category category = new Category("Tools");
            Product p1 = new Product("Hammer", "Universal hammer", 15, new BigDecimal(35.99), category);
            Product p2 = new Product("Handsaw", "Handsow using to cut wood", 15, new BigDecimal(15.99), category);
            Product p3 = new Product("Drill", "Home usage drill", 15, new BigDecimal(159.99), category);

            categoryService.addCategory(category);
            productService.addProduct(p1);
            productService.addProduct(p2);
            productService.addProduct(p3);

            System.out.println("Liczba produktów: " + productService.countAllProducts());
            System.out.println("Liczba kategorii: " + categoryService.countAllCategory());

        } else {

            System.out.println("Liczba produktów: " + productService.countAllProducts());
            System.out.println("Liczba kategorii: " + categoryService.countAllCategory());
        }
    }
}
