package com.ecommerce.model.order;

import com.ecommerce.model.address.Address;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Order {

    private Long id;

    private String orderTrackingNumber;

    private Date dateCreated;

    private Address address;
    private List<OrderItem> orderItems;

    private int totalQuantity;

    private BigDecimal totalPrice;

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    public void addOrderItem(OrderItem orderItem) {
        if(orderItem != null) {
            orderItems.add(orderItem);
        }
    }

}