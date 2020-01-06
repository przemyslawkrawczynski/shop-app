package com.tt.shop.domain.dto.requestDto;

import com.tt.shop.domain.enumvalues.OrderStatus;

public class OrderToUpdateDto {

    private Long orderId;
    private OrderStatus orderStatus;

    public OrderToUpdateDto() {
    }

    public OrderToUpdateDto(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
