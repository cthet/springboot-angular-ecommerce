package com.ecommerce.model.order;

import java.util.List;

public class OrderResponse {

    private List<Order> orderDtos;


    public OrderResponse(List<Order> orderDtos) {
        this.orderDtos = orderDtos;
    }
}
