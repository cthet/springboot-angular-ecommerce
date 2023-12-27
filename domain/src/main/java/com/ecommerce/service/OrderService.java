package com.ecommerce.service;

import com.ecommerce.exception.OrderIsNull;
import com.ecommerce.model.order.Order;
import com.ecommerce.model.order.OrderResponse;
import com.ecommerce.model.user.User;
import com.ecommerce.port.drivers.OrderDriverPort;
import com.ecommerce.port.drivers.UserDriverPort;
import com.ecommerce.port.repositories.OrderRepositoryPort;
import com.ecommerce.util.message.ErrorMessages;
import jakarta.transaction.Transactional;

import java.util.List;

public class OrderService implements OrderDriverPort {

    private final UserDriverPort userService;
    private final OrderRepositoryPort orderRepository;
    private final OrderRepositoryPort productRepository;
    private final OrderRepositoryPort addressRepository;

    public OrderService(UserDriverPort userService, OrderRepositoryPort orderRepository, OrderRepositoryPort productRepository, OrderRepositoryPort addressRepository) {
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        validateOrder(order);
        return orderRepository.save(order);
    }

    @Override
    public OrderResponse fetchUserOrders() {
        User user = userService.getUser();
        List<Order> orders = orderRepository.findOrderByUserId(user.getId());
        return new OrderResponse(orders);
    }

    private void validateOrder(Order order) {
        if (order == null) {
            throw new OrderIsNull(ErrorMessages.ORDER_IS_NULL);
        }
    }

}
