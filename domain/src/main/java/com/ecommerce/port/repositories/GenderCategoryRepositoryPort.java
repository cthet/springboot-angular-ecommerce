package com.ecommerce.port.repositories;

import com.ecommerce.model.category.GenderCategory;

import java.util.List;
import java.util.Optional;

public interface GenderCategoryRepositoryPort {

    List<GenderCategory> findAll();

    Optional<GenderCategory> findById(int gender);
}
