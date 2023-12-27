package com.ecommerce.port.drivers;

import com.ecommerce.model.category.BrandCategoriesResponse;

public interface BrandCategoryDriverPort {

    BrandCategoriesResponse getBrandCategoriesByGenderIdAndApparelCategoryId(int genderId, int apparelCategoryId);
}
