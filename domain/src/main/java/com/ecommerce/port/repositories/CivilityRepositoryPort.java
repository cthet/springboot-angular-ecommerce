package com.ecommerce.port.repositories;

import com.ecommerce.model.user.Civility;

import java.util.Optional;

public interface CivilityRepositoryPort {
    Optional<Civility> findCivilityById(int Id);
}
