package com.ecommerce.port.repositories;


import com.ecommerce.model.order.Order;

import java.util.List;

public interface OrderRepositoryPort{

    List<Order> findOrderByUserId(long id);

    Order save(Order order);
}