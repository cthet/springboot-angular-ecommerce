package com.ecommerce.port.drivers;

import com.ecommerce.model.cart.Cart;

import java.util.Optional;

public interface CartDriverPort {

    Optional<Cart> getcartfromdb();

    void saveCart(Cart cart);
}
