package com.tt.shop.service;

import com.tt.shop.domain.UserOrder;
import com.tt.shop.domain.dto.requestDto.OrderToUpdateDto;
import com.tt.shop.domain.dto.responseDto.UserOrderDto;
import com.tt.shop.exception.CartNotFoundException;
import com.tt.shop.exception.OrderNotFoundException;
import com.tt.shop.exception.ProductNotFoundException;
import com.tt.shop.exception.UserNotFoundException;
import com.tt.shop.mapper.OrderMapper;
import com.tt.shop.repository.UserOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final UserOrderRepository userOrderRepository;
    private final UserService userService;
    private final GenerateOrderService generateOrderService;
    private final OrderMapper orderMapper;

    public OrderService(UserService userService, UserOrderRepository userOrderRepository, GenerateOrderService generateOrderService, OrderMapper orderMapper) {
        this.userService = userService;
        this.userOrderRepository = userOrderRepository;
        this.generateOrderService = generateOrderService;
        this.orderMapper = orderMapper;
    }

    public UserOrderDto createNewOrderForUser(Long user_id) throws UserNotFoundException, CartNotFoundException, ProductNotFoundException {
        UserOrder newOrder = generateOrderService.realizeOrder(user_id);
        addOrder(newOrder);
        return orderMapper.mapToUserOrderDto(newOrder);
    }

    public UserOrder getOrderById(Long id) throws OrderNotFoundException {
        Optional<UserOrder> order = userOrderRepository.findById(id);
        return order.orElseThrow( () -> new OrderNotFoundException("Nie znaleziono zamówienia o podanym id: " + id));
    }

    public void addOrder(UserOrder userOrder) {
        userOrderRepository.save(userOrder);
    }

    public List<UserOrderDto> getAllUserOrdersByUserId(Long id) throws UserNotFoundException {

        if (userService.isExistById(id)) {
            List<UserOrder> orders = userOrderRepository.findAllByUserId(id);
            return orderMapper.mapToUserOrderDtoList(orders);
        } else {
            throw new UserNotFoundException("Nie znaleziono użytkownika o podanym id: " + id);
        }
    }

    public List<UserOrder> getAllUserOrders() {
        return userOrderRepository.findAll();
    }

    public List<UserOrderDto> getAllUserOrdersDto() {
        return orderMapper.mapToUserOrderDtoList(getAllUserOrders());
    }

    public List<UserOrderDto> updateStatus(OrderToUpdateDto order) throws OrderNotFoundException {
        UserOrder orderToUpdate = getOrderById(order.getOrderId());
        orderToUpdate.setOrderStatus(order.getOrderStatus());
        userOrderRepository.save(orderToUpdate);

        return orderMapper.mapToUserOrderDtoList(userOrderRepository.findAll());
    }
}
