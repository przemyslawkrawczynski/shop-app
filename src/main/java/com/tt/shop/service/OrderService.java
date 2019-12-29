package com.tt.shop.service;

import com.tt.shop.domain.UserOrder;
import com.tt.shop.exception.CartItemNotFoundException;
import com.tt.shop.exception.CartNotFoundException;
import com.tt.shop.exception.UserNotFoundException;
import com.tt.shop.repository.OrderItemRepository;
import com.tt.shop.repository.UserOrderRepository;
import org.apache.catalina.servlets.DefaultServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final UserOrderRepository userOrderRepository;
    private final OrderItemRepository orderItemRepositor;
    private final GenerateOrderService generateOrderService;

    @Autowired
    public OrderService(OrderItemRepository orderItemRepositor, UserOrderRepository userOrderRepository, GenerateOrderService generateOrderService) {
        this.orderItemRepositor = orderItemRepositor;
        this.userOrderRepository = userOrderRepository;
        this.generateOrderService = generateOrderService;
    }

    public UserOrder createNewOrderForUser(Long user_id) throws UserNotFoundException, CartNotFoundException {
        UserOrder newOrder = generateOrderService.realizeOrder(user_id);
        addOrder(newOrder);
        return newOrder;
    }

    public void addOrder(UserOrder userOrder) {
        userOrderRepository.save(userOrder);
    }

    public List<UserOrder> getAllUserOrders() {
        return userOrderRepository.findAll();
    }
}
