package com.tt.shop.controller;

import com.tt.shop.domain.dto.responseDto.UserOrderDto;
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

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserOrderDto>> getAllOrdersByUserId(@PathVariable("userId") Long userId) throws UserNotFoundException {
        List<UserOrderDto> userOrderDtos = orderService.getAllUserOrdersByUserId(userId);
        return ResponseEntity.ok(userOrderDtos);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<UserOrderDto> createOrder(@PathVariable("userId") Long userId) throws UserNotFoundException, CartNotFoundException {
        UserOrderDto orderDto = orderService.createNewOrderForUser(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
    }

}
