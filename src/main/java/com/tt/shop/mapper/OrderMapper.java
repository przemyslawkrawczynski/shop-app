package com.tt.shop.mapper;

import com.tt.shop.domain.OrderItem;
import com.tt.shop.domain.UserOrder;
import com.tt.shop.domain.dto.responseDto.OrderItemDto;
import com.tt.shop.domain.dto.responseDto.UserOrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    private final ProductMapper productMapper;

    public OrderMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public UserOrderDto mapToUserOrderDto(UserOrder userOrder) {
        return new UserOrderDto(userOrder.getId(),
                userOrder.getUser().getId(),
                mapToOrderItemDtoList(userOrder.getOrderItems()));
    }

    public List<OrderItemDto> mapToOrderItemDtoList(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(this::mapToOrderItemDto)
                .collect(Collectors.toList());
    }

    public OrderItemDto mapToOrderItemDto(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getId(),
                productMapper.mapToProductDto(orderItem.getProduct()),
                orderItem.getQuantity(),
                orderItem.getItemValue());
    }

    public List<UserOrderDto> mapToUserOrderDtoList(List<UserOrder> orders) {
        return orders.stream()
                .map(this::mapToUserOrderDto)
                .collect(Collectors.toList());
    }

}
