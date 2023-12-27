package com.ecommerce.port.repositories;

import com.ecommerce.model.category.BrandCategory;

import java.util.List;

public interface BrandCategoryRepositoryPort {

    List<BrandCategory> findByGenderCategoryIdAndProductsIsNotNull(int genderId);

    List<BrandCategory> findByGenderCategoryIdAndApparelCategoryIdAndProductsIsNotNull(int genderId, int apparelCategoryId);
}
