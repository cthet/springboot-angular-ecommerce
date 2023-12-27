package com.ecommerce.port.repositories;

import com.ecommerce.model.user.User;

import java.util.Optional;

public interface UserRepositoryPort {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    User save(User user);

    Optional<User> findById(Long id);
}
