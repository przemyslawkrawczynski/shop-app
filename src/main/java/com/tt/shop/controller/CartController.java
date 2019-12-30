package com.tt.shop.controller;

import com.tt.shop.domain.dto.requestDto.AddCartItemDto;
import com.tt.shop.domain.dto.responseDto.CartDto;
import com.tt.shop.exception.CartItemNotFoundException;
import com.tt.shop.exception.CartNotFoundException;
import com.tt.shop.exception.ProductNotFoundException;
import com.tt.shop.mapper.CartItemMapper;
import com.tt.shop.mapper.CartMapper;
import com.tt.shop.service.CartItemService;
import com.tt.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shop/carts")
public class CartController {

    private final CartService cartService;
    private final CartItemMapper cartItemMapper;
    private final CartMapper cartMapper;
    private final CartItemService cartItemService;

    @Autowired
    public CartController(CartService cartService, CartItemMapper cartItemMapper, CartMapper cartMapper, CartItemService cartItemService) {
        this.cartService = cartService;
        this.cartItemMapper = cartItemMapper;
        this.cartMapper = cartMapper;
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public ResponseEntity addCartItemToCart(@RequestBody AddCartItemDto addCartItemDto) throws CartNotFoundException, ProductNotFoundException {
        cartService.addItemToCart(cartItemMapper.mapToCartItem(addCartItemDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{cart_id}")
    public ResponseEntity<CartDto> getCartWithActiveItemsById(@PathVariable Long cart_id) throws CartNotFoundException {
        CartDto cartDto = cartMapper.mapToCartDto(cartService.getCartWithActiveItems(cart_id));
        return ResponseEntity.ok().body(cartDto);
    }

    @DeleteMapping("/{item_id}")
    public ResponseEntity deleteItemFromCart(@PathVariable Long item_id) throws CartItemNotFoundException {
        cartItemService.removeCartItemById(item_id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
