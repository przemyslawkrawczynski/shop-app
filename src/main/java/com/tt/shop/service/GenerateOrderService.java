package com.tt.shop.service;

import com.tt.shop.domain.CartItem;
import com.tt.shop.domain.OrderItem;
import com.tt.shop.domain.User;
import com.tt.shop.domain.UserOrder;
import com.tt.shop.exception.CartNotFoundException;
import com.tt.shop.exception.ProductNotFoundException;
import com.tt.shop.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenerateOrderService {

    private final UserService userService;
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final ProductService productService;

    public GenerateOrderService(UserService userService, CartService cartService, CartItemService cartItemService, ProductService productService) {
        this.userService = userService;
        this.cartService = cartService;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    public UserOrder realizeOrder(Long userId) throws UserNotFoundException, CartNotFoundException, ProductNotFoundException {

        User user = userService.getUserById(userId);
        List<CartItem> activeCartItems = cartService
                .getCartWithActiveItems(user.getCart().getId())
                .getItemsInCart();

        return generateOrder(user, activeCartItems);
    }

    public UserOrder generateOrder(User user, List<CartItem> activeCartItems) throws ProductNotFoundException {

        UserOrder order = new UserOrder(user);
        List<OrderItem> orderItemList = generateOrderItemList(order, activeCartItems);
        order.setOrderItems(orderItemList);

        return order;
    }

    @Transactional
    public List<OrderItem> generateOrderItemList(UserOrder userOrder, List<CartItem> cartItems) throws ProductNotFoundException {

        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> doOrderCartItem(userOrder, item))
                .collect(Collectors.toList());

        cartItemService.setStatusAfterOrder(cartItems);
        productService.updateStorageQuantity(orderItems);

        return orderItems;
    }

    public OrderItem doOrderCartItem(UserOrder userOrder, CartItem cartItem) {

        return new OrderItem(userOrder,
                cartItem.getProduct(),
                cartItem.getQuantity());
    }

}
