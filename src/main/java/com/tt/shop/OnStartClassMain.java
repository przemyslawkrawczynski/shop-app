package com.tt.shop;

import com.tt.shop.domain.Category;
import com.tt.shop.domain.Product;
import com.tt.shop.domain.User;
import com.tt.shop.domain.enumvalues.Role;
import com.tt.shop.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class OnStartClassMain implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public OnStartClassMain(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    private final String DESCRIPTION = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";

    @Override
    public void run(String... args) throws Exception {

        Category[] categories = {
                new Category("Artykuły przemysłowe"),
                new Category("Śruby"),
                new Category("Narzędzia"),
                new Category("Chemia")
        };

        if (categoryRepository.count() == 0) {
            categoryRepository.saveAll(Arrays.asList(categories));
        }


        Product[] products = {

                new Product("Papier ścierny", DESCRIPTION, 120, new BigDecimal(9.99), categories[0]),
                new Product("Miarka uniwersalna", DESCRIPTION, 120, new BigDecimal(15.99), categories[0]),
                new Product("Poziomica", DESCRIPTION, 120, new BigDecimal(7.99), categories[0]),
                new Product("Kabel elektryczny", DESCRIPTION, 120, new BigDecimal(5.99), categories[0]),
                new Product("Wiertło", DESCRIPTION, 120, new BigDecimal(9.99), categories[0]),


                new Product("Śruby", DESCRIPTION, 120, new BigDecimal(0.99), categories[1]),
                new Product("Śruby zamkowe", DESCRIPTION, 120, new BigDecimal(0.99), categories[1]),
                new Product("Śruby sześciokątne", DESCRIPTION, 120, new BigDecimal(0.99), categories[1]),
                new Product("Śruby noskowe", DESCRIPTION, 120, new BigDecimal(0.99), categories[1]),
                new Product("Śruby imbusowe", DESCRIPTION, 120, new BigDecimal(0.99), categories[1]),
                new Product("Śruby długie", DESCRIPTION, 120, new BigDecimal(0.99), categories[1]),

                new Product("Młotek", DESCRIPTION, 120, new BigDecimal(10.99), categories[2]),
                new Product("Śrubokręt", DESCRIPTION, 120, new BigDecimal(20.99), categories[2]),
                new Product("Kombinerki", DESCRIPTION, 120, new BigDecimal(9.99), categories[2]),
                new Product("Szczypce", DESCRIPTION, 120, new BigDecimal(7.99), categories[2]),
                new Product("Klucze", DESCRIPTION, 120, new BigDecimal(15.99), categories[2]),
                new Product("Imbusy", DESCRIPTION, 120, new BigDecimal(19.99), categories[2]),

                new Product("Farba dekoral", DESCRIPTION, 20, new BigDecimal(10.99), categories[3]),
                new Product("Farba biała", DESCRIPTION, 25, new BigDecimal(20.99), categories[3]),
                new Product("Klej do drewna", DESCRIPTION, 35, new BigDecimal(9.99), categories[3]),
                new Product("Zmywacz", DESCRIPTION, 45, new BigDecimal(7.99), categories[3]),
                new Product("Lakier do drewna", DESCRIPTION, 35, new BigDecimal(15.99), categories[3]),
                new Product("Lakier do metalu", DESCRIPTION, 121, new BigDecimal(19.99), categories[3]),

        };

        if (productRepository.count() == 0) {
            productRepository.saveAll(Arrays.asList(products));
        }


        User user = new User("Johny", "Bravo", "user@user.com", "secretPass", true, Role.ROLE_USER);
        User user2 = new User("Mickey", "Mouse", "admin@admin.com", "secretPass", true, Role.ROLE_ADMIN);

        if (userRepository.count() == 0) {
            userRepository.save(user);
            userRepository.save(user2);
        }

        //Print
        System.out.println("Liczba produktów: " + productRepository.count());
        System.out.println("Liczba kategorii: " + categoryRepository.count());
        System.out.println("Liczba użytkowników: " + userRepository.count());

        userRepository.findAll().stream()
                .forEach(s -> System.out.println(s.getName() + " " + " | Pozycje w koszyku [" + s.getCart().getItemsInCart().size() + "]"));
    }
}
