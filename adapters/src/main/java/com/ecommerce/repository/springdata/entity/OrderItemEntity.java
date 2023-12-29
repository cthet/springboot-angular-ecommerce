package com.ecommerce.repository.springdata.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_item")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private int quantity;

    private BigDecimal amount;

    @OneToOne
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }
}

