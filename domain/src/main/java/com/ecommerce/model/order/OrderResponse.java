package com.ecommerce.model.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponse {

    private List<Order> orderDtos;


    public OrderResponse(List<Order> orderDtos) {
        this.orderDtos = orderDtos;
    }
}
