package com.tt.shop.domain.dto.responseDto;

import java.math.BigDecimal;

public class CartItemDto {

    private Long id;
    private ProductDto productDto;
    private int quantity;
    private BigDecimal itemValue;

    public CartItemDto() {}

    public CartItemDto(Long id, ProductDto productDto, int quantity, BigDecimal itemValue) {
        this.id = id;
        this.productDto = productDto;
        this.quantity = quantity;
        this.itemValue = itemValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getItemValue() {
        return itemValue;
    }

    public void setItemValue(BigDecimal itemValue) {
        this.itemValue = itemValue;
    }
}
