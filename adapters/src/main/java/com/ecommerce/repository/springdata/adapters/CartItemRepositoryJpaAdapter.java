package com.ecommerce.repository.springdata.adapters;

import com.ecommerce.port.adapters.repositories.CartItemRepositoryPort;
import com.ecommerce.repository.springdata.repository.CartItemJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CartItemRepositoryJpaAdapter implements CartItemRepositoryPort {

    private CartItemJpaRepository cartItemJpaRepository;

    public CartItemRepositoryJpaAdapter(CartItemJpaRepository cartItemJpaRepository) {
        this.cartItemJpaRepository = cartItemJpaRepository;
    }


    @Override
    public void deleteById(Long Id) {
        cartItemJpaRepository.deleteById(Id);
    }
}
