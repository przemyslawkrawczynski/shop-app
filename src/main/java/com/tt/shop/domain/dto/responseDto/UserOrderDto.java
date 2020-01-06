package com.tt.shop.domain.dto.responseDto;

import com.tt.shop.domain.enumvalues.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserOrderDto {

    private Long orderId;
    private Long userId;
    private List<OrderItemDto> orderItemList;
    private Integer numberOfItem;
    private BigDecimal orderValue;
    private String createDate;
    private OrderStatus orderStatus;

    public UserOrderDto(Long order_id, Long user_id, List<OrderItemDto> orderItemList, LocalDateTime createdDate, OrderStatus orderStatus) {
        this.orderId = order_id;
        this.userId = user_id;
        this.orderItemList = orderItemList;
        this.numberOfItem = orderItemList.size();
        this.orderValue = getAllItemsValue(orderItemList);
        this.createDate = createdDate.format(DateTimeFormatter.ISO_DATE);
        this.orderStatus = orderStatus;
    }

    public BigDecimal getAllItemsValue(List<OrderItemDto> items) {
        BigDecimal value = items.stream()
                .map(OrderItemDto::getItemValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return value;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Integer getNumberOfItem() {
        return numberOfItem;
    }

    public void setNumberOfItem(Integer numberOfItem) {
        this.numberOfItem = numberOfItem;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
