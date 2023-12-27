package com.ecommerce.port.drivers;

import com.ecommerce.model.cart.Cart;

public interface CartDriverPort {

    Cart getCartFromDB();

    void saveCart(Cart cart);
}
