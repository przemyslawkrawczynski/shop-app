package com.tt.shop.controller.management;

import com.tt.shop.mapper.CartMapper;
import com.tt.shop.service.CartItemService;
import com.tt.shop.service.CartService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shop/management/cart")
public class CartManagementController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final CartMapper cartMapper;

    public CartManagementController(CartService cartService, CartItemService cartItemService, CartMapper cartMapper) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
        this.cartMapper = cartMapper;
    }

}
