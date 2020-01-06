package com.tt.shop.domain.dto.requestDto;

public class CartItemUpdateDto {

    private Long id;
    private int quantity;

    private CartItemUpdateDto() {}

    public CartItemUpdateDto(Long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
