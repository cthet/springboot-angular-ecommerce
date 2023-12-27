package com.ecommerce.model.cart;

import com.ecommerce.model.product.Product;
import com.ecommerce.model.user.User;

import java.math.BigDecimal;

public class CartItem {

    private int quantity;

    private BigDecimal amount;

    private Product product;

    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }


    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
