package com.tt.shop.domain.dto.responseDto;

import java.util.List;

public class CartDto {

    private Long cart_id;
    private Long user_id;
    private List<CartItemDto> cartItemDtos;

    public CartDto() {}

    public CartDto(Long cart_id, Long user_id, List<CartItemDto> cartItemDtos) {
        this.cart_id = cart_id;
        this.user_id = user_id;
        this.cartItemDtos = cartItemDtos;
    }

    public Long getCart_id() {
        return cart_id;
    }

    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<CartItemDto> getCartItemDtos() {
        return cartItemDtos;
    }

    public void setCartItemDtos(List<CartItemDto> cartItemDtos) {
        this.cartItemDtos = cartItemDtos;
    }
}
