//package com.ecommerce.repository.springdata.mappers;
//
//import com.ecommerce.model.order.OrderItem;
//import com.ecommerce.repository.springdata.entity.OrderItemEntity;
//
//public class OrderItemConverter {
//
//    public static OrderItem toOrderItem(OrderItemEntity orderItemEntity) {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setProduct(ProductConverter.toProduct(orderItemEntity.getProductEntity()));
//        orderItem.setQuantity(orderItemEntity.getQuantity());
//        orderItem.setAmount(orderItemEntity.getAmount());
//        return orderItem;
//    }
//
//    public static OrderItemEntity toOrderItemEntity(OrderItem orderItem) {
//        OrderItemEntity orderItemEntity = new OrderItemEntity();
//        orderItemEntity.setProductEntity(ProductConverter.toProductEntity(orderItem.getProduct()));
//        orderItemEntity.setQuantity(orderItem.getQuantity());
//        orderItemEntity.setAmount(orderItem.getAmount());
//        return orderItemEntity;
//    }
//}
