//package com.ecommerce.repository.springdata.mappers;
//
//import com.ecommerce.model.order.Order;
//import com.ecommerce.model.order.OrderItem;
//import com.ecommerce.repository.springdata.entity.OrderEntity;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class OrderConverter {
//
//    public static Order toOrder(OrderEntity orderEntity) {
//        if ( orderEntity == null ) {
//            return null;
//        }
//        Order order = new Order();
//        order.setId(orderEntity.getId());
//        order.setOrderTrackingNumber(orderEntity.getOrderTrackingNumber());
//        order.setDateCreated(orderEntity.getDateCreated());
//        order.setAddress(AddressMapper.toAddress(orderEntity.getAddress()));
//        order.setTotalQuantity(orderEntity.getTotalQuantity());
//        order.setTotalPrice(orderEntity.getTotalPrice());
//
//        List<OrderItem> orderItems = orderEntity.getOrderItems().stream()
//                .map(OrderItemConverter::toOrderItem)
//                .collect(Collectors.toList());
//        order.setOrderItems(orderItems);
//
//        return order;
//    }
//}
