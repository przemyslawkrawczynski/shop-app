package com.tt.shop;

import com.tt.shop.domain.Category;
import com.tt.shop.domain.Product;
import com.tt.shop.domain.User;
import com.tt.shop.domain.enumvalues.Role;
import com.tt.shop.repository.CartItemRepository;
import com.tt.shop.repository.CartRepository;
import com.tt.shop.repository.UserRepository;
import com.tt.shop.service.CategoryService;
import com.tt.shop.service.ProductService;
import com.tt.shop.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OnStartClassMain implements CommandLineRunner {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public OnStartClassMain(ProductService productService, CategoryService categoryService, UserService userService, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (categoryService.countAllCategory() == 0 &&
                        productService.countAllProducts() == 0 &&
                        cartItemRepository.count() == 0 &&
                        cartRepository.count() == 0 &&
                        userService.countAllUsers() == 0) {

            Category category = new Category("Tools");
            Product p1 = new Product("Hammer", "Universal hammer", 15, new BigDecimal(35.99), category);
            Product p2 = new Product("Handsaw", "Handsow using to cut wood", 15, new BigDecimal(15.99), category);
            Product p3 = new Product("Drill", "Home usage drill", 15, new BigDecimal(159.99), category);

            User user = new User("Johny", "Bravo", "jb@hot.com", "secretPass", true, Role.ROLE_USER);
            User user2 = new User("Mickey", "Mouse", "mm@riders.com", "secretPass", true, Role.ROLE_ADMIN);

            userService.addUser(user);
            userService.addUser(user2);
            categoryService.addCategory(category);
            productService.addProduct(p1);
            productService.addProduct(p2);
            productService.addProduct(p3);

        }

        //Print
        System.out.println("Liczba produktów: " + productService.countAllProducts());
        System.out.println("Liczba kategorii: " + categoryService.countAllCategory());
        System.out.println("Liczba użytkowników: " + userService.countAllUsers());
        userService.getAllUserList()
                .stream()
                .forEach(s -> System.out.println(s.getName() + " " + " | Pozycje w koszyku [" + s.getCart().getItemsInCart().size() +"]" ));
    }
}
