package com.tt.shop.service;

import com.tt.shop.domain.*;
import com.tt.shop.domain.dto.responseDto.CartDto;
import com.tt.shop.domain.dto.responseDto.CartItemDto;
import com.tt.shop.domain.enumvalues.CartItemStatus;
import com.tt.shop.domain.enumvalues.Role;
import com.tt.shop.exception.CartNotFoundException;
import com.tt.shop.mapper.CartMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTestSuits {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartMapper cartMapper;

    @Test
    public void testCreateNewOrderForUser() throws CartNotFoundException {

        //Given
        User user = new User("Johny", "Bravo", "jb@hot.com", "secretPass", true, Role.ROLE_USER);
        Category category1 = new Category("Tools");
        Product p1 = new Product("Hammer", "Universal hammer", 15, new BigDecimal(35.99), category1);
        Product p2 = new Product("Handsaw", "Handsow using to cut wood", 15, new BigDecimal(15.99), category1);
        Product p3 = new Product("Drill", "Home usage drill", 15, new BigDecimal(159.99), category1);

        userService.addUser(user);
        categoryService.addCategory(category1);
        productService.addProduct(p1);
        productService.addProduct(p2);
        productService.addProduct(p3);

        Cart userCart = user.getCart();
        CartItem cartItem = new CartItem(userCart, p1, 1, p1.getPrice().multiply(new BigDecimal(1)), CartItemStatus.IN_CART);
        CartItem cartItem2 = new CartItem(userCart, p1, 1, p1.getPrice().multiply(new BigDecimal(1)), CartItemStatus.IN_CART);
        CartItem cartItem3 = new CartItem(userCart, p1, 1, p1.getPrice().multiply(new BigDecimal(1)), CartItemStatus.IN_CART);
        cartService.addItemToCart(cartItem);
        cartService.addItemToCart(cartItem2);
        cartService.addItemToCart(cartItem3);

//        user.setCart(userCart);
//        userService.addUser(user);

        CartDto cartDto = cartMapper.mapToCartDto(cartService.getCartById(user.getCart().getId()));

        System.out.println(cartDto);

    }


}
