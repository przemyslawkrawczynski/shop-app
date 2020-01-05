package com.tt.shop.controller.management;

import com.tt.shop.domain.dto.responseDto.UserOrderDto;
import com.tt.shop.service.CartItemService;
import com.tt.shop.service.CartService;
import com.tt.shop.service.OrderService;
import com.tt.shop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shop/management")
public class CartManagementController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final OrderService orderService;
    private final UserService userService;

    public CartManagementController(CartService cartService, CartItemService cartItemService, OrderService orderService, UserService userService) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<UserOrderDto>> getAllUserOrders() {
        List<UserOrderDto> orders = orderService.getAllUserOrdersDto();
        return ResponseEntity.ok().body(orders);
    }


}
