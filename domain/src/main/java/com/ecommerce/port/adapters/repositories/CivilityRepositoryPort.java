package com.ecommerce.port.adapters.repositories;

import com.ecommerce.model.user.Civility;

import java.util.Optional;

public interface CivilityRepositoryPort {
    Civility findCivilityById(int Id);
}
