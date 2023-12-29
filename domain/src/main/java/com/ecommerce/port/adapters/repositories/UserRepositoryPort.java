package com.ecommerce.port.adapters.repositories;

import com.ecommerce.model.user.User;


public interface UserRepositoryPort {
    boolean existsByEmail(String email);

    User findByEmail(String email);

    User save(User user);
    User findById(Long userId);
}
