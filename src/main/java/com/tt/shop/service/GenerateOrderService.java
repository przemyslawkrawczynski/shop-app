package com.tt.shop.service;

import com.tt.shop.domain.CartItem;
import com.tt.shop.domain.OrderItem;
import com.tt.shop.domain.User;
import com.tt.shop.domain.UserOrder;
import com.tt.shop.exception.CartItemNotFoundException;
import com.tt.shop.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenerateOrderService {

    private UserService userService;
    private CartItemService cartItemService;

    public GenerateOrderService(UserService userService) {
        this.userService = userService;
    }

    public UserOrder realizeOrder(Long user_id) throws UserNotFoundException, CartItemNotFoundException {

        User user = userService.getUserById(user_id);
        List<CartItem> activeCartItems = cartItemService.getAllActiveCartItemsInCart(user.getCart().getId());

        return generateOrder(user, activeCartItems);

    }

    public UserOrder generateOrder(User user, List<CartItem> activeCartItems) {

        UserOrder order = new UserOrder(user);
        List<OrderItem> orderItemList = generateOrderItemList(order, activeCartItems);
        order.setOrderItems(orderItemList);

        return order;
    }


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
