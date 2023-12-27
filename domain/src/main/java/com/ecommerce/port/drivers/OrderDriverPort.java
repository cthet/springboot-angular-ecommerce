package com.ecommerce.port.drivers;

import com.ecommerce.model.order.Order;
import com.ecommerce.model.order.OrderResponse;

public interface OrderDriverPort {

    Order saveOrder(Order order);

    OrderResponse fetchUserOrders();
}
