package com.ecommerce.port.repositories;


import com.ecommerce.model.category.BrandCategoryImage;

import java.util.Optional;

public interface BrandCategoryImageRepositoryPort {

    Optional<BrandCategoryImage> findByBrandCategoryIdAndGenderCategoryId(int brandId, int gender);
}
