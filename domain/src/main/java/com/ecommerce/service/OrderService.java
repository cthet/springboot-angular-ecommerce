package com.ecommerce.service;

import com.ecommerce.exception.OrderIsNull;
import com.ecommerce.model.order.Order;
import com.ecommerce.model.order.OrderResponse;
import com.ecommerce.model.user.User;
import com.ecommerce.port.drivers.OrderDriverPort;
import com.ecommerce.port.adapters.gateway.AuthenticationGateway;
import com.ecommerce.port.adapters.repositories.OrderRepositoryPort;
import com.ecommerce.util.message.ErrorMessages;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public class OrderService implements OrderDriverPort {

    private final AuthenticationGateway authenticationGateway;
    private final OrderRepositoryPort orderRepository;
    private final OrderRepositoryPort productRepository;
    private final OrderRepositoryPort addressRepository;


    public OrderService(AuthenticationGateway authenticationGateway, OrderRepositoryPort orderRepository, OrderRepositoryPort productRepository, OrderRepositoryPort addressRepository) {
        this.authenticationGateway = authenticationGateway;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        validateOrder(order);
        setOrderTrackingNumber(order);
        setAuthenticatedUser(order);
        return orderRepository.save(order);
    }

    private void setOrderTrackingNumber(Order order) {
        order.setOrderTrackingNumber(generateOrderTrackingNumber());
    }

    @Override
    public OrderResponse fetchUserOrders() {
        User user = authenticationGateway.getAuthenticatedUser();
        List<Order> orders = orderRepository.findOrderByUserId(user.getId());
        return new OrderResponse(orders);
    }

    private void validateOrder(Order order) {
        if (order == null) {
            throw new OrderIsNull(ErrorMessages.ORDER_IS_NULL);
        }
    }

    private String generateOrderTrackingNumber () {
        return UUID.randomUUID().toString();
    }

    private void setAuthenticatedUser(Order order) {
        User user = authenticationGateway.getAuthenticatedUser();
        order.setUser(user);
    }

}
