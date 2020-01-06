package com.tt.shop.domain.dto.responseDto;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {

    private Long cart_id;
    private Long user_id;
    private List<CartItemDto> cartItemDtos;
    private BigDecimal cartValue;

    public CartDto() {}

    public CartDto(Long cart_id, Long user_id, List<CartItemDto> cartItemDtos, BigDecimal cartValue) {
        this.cart_id = cart_id;
        this.user_id = user_id;
        this.cartItemDtos = cartItemDtos;
        this.cartValue = cartValue;
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

    public BigDecimal getCartValue() {
        return cartValue;
    }

    public void setCartValue(BigDecimal cartValue) {
        this.cartValue = cartValue;
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "cart_id=" + cart_id +
                ", user_id=" + user_id +
                ", cartItemDtos=" + cartItemDtos +
                '}';
    }
}
