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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final UserOrderRepository userOrderRepository;
    private final UserService userService;
    private final GenerateOrderService generateOrderService;

    @Autowired
    public OrderService(UserService userService,UserOrderRepository userOrderRepository, GenerateOrderService generateOrderService) {
        this.userService = userService;
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

    public List<UserOrder> getAllUserOrdersByUserId(Long id) throws UserNotFoundException {
       //bez optionalas or else

        if (userService.isExistById(id)) {
            Optional<List<UserOrder>> opt = userOrderRepository.findAllByUserId(id);
            return opt.orElse(new ArrayList<>());
        } else {
            throw new UserNotFoundException("Nie znaleziono użytkownika o podanym id: " + id);
        }
    }
    public List<UserOrder> getAllUserOrders() {
        return userOrderRepository.findAll();
    }
}
