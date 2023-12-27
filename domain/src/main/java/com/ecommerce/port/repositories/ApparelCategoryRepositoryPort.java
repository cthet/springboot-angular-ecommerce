package com.ecommerce.port.repositories;

import com.ecommerce.model.category.ApparelCategory;

import java.util.List;

public interface ApparelCategoryRepositoryPort {

    List<ApparelCategory> findAll();

    List<ApparelCategory> findByGenderCategoryIdAndProductsIsNotNull(int genderId);

    List<ApparelCategory> findByBrandCategoryIdAndGenderCategoryIdAndProductsIsNotNull(int brandId, int genderId);
}