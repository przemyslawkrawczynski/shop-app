package com.tt.shop.service;

import com.tt.shop.domain.CartItem;
import com.tt.shop.domain.OrderItem;
import com.tt.shop.domain.User;
import com.tt.shop.domain.UserOrder;
import com.tt.shop.domain.dto.responseDto.UserOrderDto;
import com.tt.shop.exception.CartNotFoundException;
import com.tt.shop.exception.UserNotFoundException;
import com.tt.shop.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenerateOrderService {

    private final UserService userService;
    private final CartService cartService;
    private final CartItemService cartItemService;

    public GenerateOrderService(UserService userService, CartService cartService, CartItemService cartItemService) {
        this.userService = userService;
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }

    public UserOrder realizeOrder(Long userId) throws UserNotFoundException, CartNotFoundException {

        User user = userService.getUserById(userId);
        List<CartItem> activeCartItems = cartService
                .getCartWithActiveItems(user.getCart().getId())
                .getItemsInCart();

        return generateOrder(user, activeCartItems);
    }

    public UserOrder generateOrder(User user, List<CartItem> activeCartItems) {

        UserOrder order = new UserOrder(user);
        List<OrderItem> orderItemList = generateOrderItemList(order, activeCartItems);
        order.setOrderItems(orderItemList);

        return order;
    }

    @Transactional
    public List<OrderItem> generateOrderItemList(UserOrder userOrder, List<CartItem> cartItems) {

        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> doOrderCartItem(userOrder, item))
                .collect(Collectors.toList());

        cartItemService.setStatusAfterOrder(cartItems);

        return orderItems;
    }

    public OrderItem doOrderCartItem(UserOrder userOrder, CartItem cartItem) {

        return new OrderItem(userOrder,
                cartItem.getProduct(),
                cartItem.getQuantity());
    }

}
