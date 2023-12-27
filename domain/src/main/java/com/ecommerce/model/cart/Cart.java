package com.ecommerce.model.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {

    List<CartItem> cartItems = new ArrayList<>();

    private int totalQuantity;

    private BigDecimal totalPrice;

    public List<CartItem> getCartItems() {
        return Collections.unmodifiableList(cartItems);
    }

    public void addCartItem(CartItem cartItem) {
        if(cartItem != null) {
            this.cartItems.add(cartItem);
            cartItem.setCart(this);
        }
    }

    public void clearCartItem(){
        this.cartItems.clear();
    }

    public void deleteCartItem(CartItem item){
        if(item != null){
            this.cartItems.remove(item);
        }
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }


    public int getTotalQuantity() {
        return totalQuantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
