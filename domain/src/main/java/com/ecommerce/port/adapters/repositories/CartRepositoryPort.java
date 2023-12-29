package com.ecommerce.port.adapters.repositories;


import com.ecommerce.model.cart.Cart;

import java.util.Optional;

public interface CartRepositoryPort {


    Optional<Cart> findCartByUserId(long id);

    void save(Cart cart);
}
