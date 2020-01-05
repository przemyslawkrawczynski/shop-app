package com.tt.shop.service;

import com.tt.shop.domain.Cart;
import com.tt.shop.domain.Category;
import com.tt.shop.domain.Product;
import com.tt.shop.domain.User;
import com.tt.shop.domain.dto.requestDto.AddCartItemDto;
import com.tt.shop.domain.dto.responseDto.CartDto;
import com.tt.shop.domain.enumvalues.Role;
import com.tt.shop.exception.CartNotFoundException;
import com.tt.shop.exception.ProductNotFoundException;
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
    public void testCreateNewOrderForUser() throws CartNotFoundException, ProductNotFoundException {

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
        AddCartItemDto cartItem = new AddCartItemDto(userCart.getId(), p1.getId(), 1);
        AddCartItemDto cartItem1 = new AddCartItemDto(userCart.getId(), p2.getId(), 1);
        AddCartItemDto cartItem2 = new AddCartItemDto(userCart.getId(), p3.getId(), 1);
        cartService.addItemToCart(cartItem);
        cartService.addItemToCart(cartItem1);
        cartService.addItemToCart(cartItem2);

//        user.setCart(userCart);
//        userService.addUser(user);

        CartDto cartDto = cartMapper.mapToCartDto(cartService.getCartById(user.getCart().getId()));

        System.out.println(cartDto);

    }


}
