package com.tt.shop.domain.dto.responseDto;

import java.math.BigDecimal;

public class OrderItemDto {

    private long itemId;
    private ProductDto productDto;
    private int quantity;
    private BigDecimal itemValue;

    public OrderItemDto(long itemId, ProductDto productDto, int quantity, BigDecimal itemValue) {
        this.itemId = itemId;
        this.productDto = productDto;
        this.quantity = quantity;
        this.itemValue = itemValue;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
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
