package com.tt.shop.controller;

import com.tt.shop.domain.dto.requestDto.AddCartItemDto;
import com.tt.shop.domain.dto.responseDto.CartDto;
import com.tt.shop.exception.CartItemNotFoundException;
import com.tt.shop.exception.CartNotFoundException;
import com.tt.shop.exception.ProductNotFoundException;
import com.tt.shop.service.CartItemService;
import com.tt.shop.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shop/carts")
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;

    public CartController(CartService cartService, CartItemService cartItemService) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public ResponseEntity addCartItemToCart(@RequestBody AddCartItemDto addCartItemDto) throws CartNotFoundException, ProductNotFoundException {
        cartService.addItemToCart(addCartItemDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCartWithActiveItemsById(@PathVariable Long cartId) throws CartNotFoundException {
        CartDto cartDto = cartService.getCartDtoWithActiveItems(cartId);
        return ResponseEntity.ok().body(cartDto);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity deleteItemFromCart(@PathVariable Long itemId) throws CartItemNotFoundException {
        cartItemService.removeCartItemById(itemId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
