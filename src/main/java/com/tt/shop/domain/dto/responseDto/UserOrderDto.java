package com.tt.shop.domain.dto.responseDto;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class UserOrderDto {

    private Long order_id;
    private Long user_id;
    private List<OrderItemDto> orderItemList;
    private BigDecimal orderValue;

    public UserOrderDto(Long order_id, Long user_id, List<OrderItemDto> orderItemList) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.orderItemList = orderItemList;
        this.orderValue = getAllItemsValue(orderItemList);
    }

    public BigDecimal getAllItemsValue(List<OrderItemDto> items) {
        BigDecimal value = items.stream()
                .map(OrderItemDto::getItemValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return value;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<OrderItemDto> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemDto> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public BigDecimal getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(BigDecimal orderValue) {
        this.orderValue = orderValue;
    }
}
