package com.ecommerce.port.adapters.repositories;

import com.ecommerce.model.category.GenderCategory;

import java.util.List;
import java.util.Optional;

public interface GenderCategoryRepositoryPort {

    List<GenderCategory> findAll();

    GenderCategory findById(int gender);
}
