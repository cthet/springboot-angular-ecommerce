package com.ecommerce.port.adapters.repositories;


import com.ecommerce.model.category.BrandCategoryImage;

import java.util.Optional;

public interface BrandCategoryImageRepositoryPort {

    BrandCategoryImage findByBrandCategoryIdAndGenderCategoryId(int brandId, int gender);
}
