package com.tt.shop.service;

import com.tt.shop.domain.Category;
import com.tt.shop.domain.Product;
import com.tt.shop.exception.CategoryNotFoundException;
import com.tt.shop.exception.ProductNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
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

        //Then
        Assert.assertEquals(productListSizeBeforeAddProduct - 1, productListSizeAfterAddProduct);
        Assert.assertFalse(isExist);

    }

    @Test
    public void testCountAllProducts() {

        //Given
        Category category = new Category("Tools");
        Product p1 = new Product("Hammer", "Universal hammer", 15, new BigDecimal(35.99), category);
        Product p2 = new Product("Handsaw", "Handsow using to cut wood", 15, new BigDecimal(15.99), category);
        Product p3 = new Product("Drill", "Home usage drill", 15, new BigDecimal(159.99), category);


        //When
        long countAllProducts = productService.countAllProducts();

        //Then
        Assert.assertEquals(3L, countAllProducts);

    }

    @Test
    public void testGetAllProducts() {

        //Given
        Category category = new Category("Tools");
        Product p1 = new Product("Hammer", "Universal hammer", 15, new BigDecimal(35.99), category);
        Product p2 = new Product("Handsaw", "Handsow using to cut wood", 15, new BigDecimal(15.99), category);
        Product p3 = new Product("Drill", "Home usage drill", 15, new BigDecimal(159.99), category);

        //When
        BigDecimal allAddedValueFromTestCaseP1P2P3 = p1.getPrice().add(p2.getPrice().add(p3.getPrice())).round(new MathContext(2));
        List<Product> products = productService.getAllProducts();

        BigDecimal allSingleProductsValueFromList = products
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        //Then
        Assert.assertEquals(3, products.size());
        Assert.assertEquals(roundToNumberAfterComa(allAddedValueFromTestCaseP1P2P3,2),
                roundToNumberAfterComa(allSingleProductsValueFromList,2));

    }

    @Test
    public void GetProductByIdIfNotExistCatchException() throws ProductNotFoundException {

        //Given
        Category category = new Category("Tools");
        Product p1 = new Product("Hammer", "Universal hammer", 15, new BigDecimal(35.99), category);

        categoryService.addCategory(category);
        productService.addProduct(p1);

        List<Product> products = productService.getAllProducts();
        long id = products.get(0).getId();

        //When
        boolean isCatchException = false;
        Product product = productService.getProductById(id);

        try {
            Random r = new Random();
            productService.getProductById(id + r.nextInt(100));
        } catch (ProductNotFoundException ex) {
            isCatchException = true;
        }

        Assert.assertEquals(p1.getName(), product.getName());
        Assert.assertEquals(roundToNumberAfterComa(p1.getPrice(),2),
                roundToNumberAfterComa(product.getPrice(),2));
        Assert.assertTrue(isCatchException);

    }

    @Test
    public void testGetAllProductsByCategoryId() throws CategoryNotFoundException {

        //Given
        Category category1 = new Category("Tools");
        Product p1 = new Product("Hammer", "Universal hammer", 15, new BigDecimal(35.99), category1);
        Product p2 = new Product("Handsaw", "Handsow using to cut wood", 15, new BigDecimal(15.99), category1);
        Product p3 = new Product("Drill", "Home usage drill", 15, new BigDecimal(159.99), category1);

        Category category2 = new Category("Articles");
        Product p4 = new Product("Pen", "Blue pen", 1, new BigDecimal(6), category2);
        Product p5 = new Product("Pencil", "Big pencil", 4, new BigDecimal(9), category2);

        categoryService.addCategory(category1);
        categoryService.addCategory(category2);

        productService.addProduct(p1);
        productService.addProduct(p2);
        productService.addProduct(p3);
        productService.addProduct(p4);
        productService.addProduct(p5);

        //When
        long c1_ID = getCategoryId(category1);
        long c2_ID = getCategoryId(category2);

        int numberOfProductsFromCategory1 = productService.getAllProductsByCategoryId(c1_ID).size();
        int numberOfProductsFromCategory2 = productService.getAllProductsByCategoryId(c2_ID).size();

        //Then
        Assert.assertEquals(3, numberOfProductsFromCategory1);
        Assert.assertEquals(2, numberOfProductsFromCategory2);

    }

    private BigDecimal roundToNumberAfterComa(BigDecimal bigDecimal, int number) {
        return bigDecimal.round(new MathContext(2));
    }

    private long getCategoryId(Category category) throws CategoryNotFoundException {
        return categoryService.getCategoryByName(category.getName()).getId();
    }
}
