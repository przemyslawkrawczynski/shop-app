package com.tt.shop.controller;

import com.tt.shop.domain.UserOrder;
import com.tt.shop.domain.dto.responseDto.UserOrderDto;
import com.tt.shop.exception.CartItemNotFoundException;
import com.tt.shop.exception.CartNotFoundException;
import com.tt.shop.exception.UserNotFoundException;
import com.tt.shop.mapper.OrderMapper;
import com.tt.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<List<UserOrderDto>> getAllOrdersByUserId(@PathVariable("user_id") Long user_id) throws UserNotFoundException {
        List<UserOrderDto> userOrderDtos = orderMapper.mapToUserOrderDtoList(orderService.getAllUserOrdersByUserId(user_id));
        return ResponseEntity.ok(userOrderDtos);
    }

    @PostMapping("/{user_id}")
    public ResponseEntity<UserOrderDto> createOrder(@PathVariable("user_id") Long user_id) throws UserNotFoundException, CartNotFoundException {
        UserOrderDto orderDto = orderMapper.mapToUserOrderDto(orderService.createNewOrderForUser(user_id));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
    }



}
