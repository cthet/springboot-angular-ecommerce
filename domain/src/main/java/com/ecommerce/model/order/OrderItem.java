package com.ecommerce.model.order;

import com.ecommerce.model.product.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItem {

    private Product product;

    private int quantity;

    private BigDecimal amount;


}
