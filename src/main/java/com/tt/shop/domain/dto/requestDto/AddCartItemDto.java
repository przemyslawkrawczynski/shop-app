package com.tt.shop.domain.dto.requestDto;

public class AddCartItemDto {

    private Long cart_id;
    private Long product_id;
    private int quantity;

    public AddCartItemDto(Long cart_id, Long product_id, int quantity) {
        this.cart_id = cart_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public AddCartItemDto() {}

    public Long getCart_id() {
        return cart_id;
    }

    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItemDto{" +
                "cart_id=" + cart_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                '}';
    }
}
