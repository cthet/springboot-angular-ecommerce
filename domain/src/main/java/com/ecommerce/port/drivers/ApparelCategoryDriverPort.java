package com.ecommerce.port.drivers;

import com.ecommerce.model.category.ApparelCategoriesResponse;

public interface ApparelCategoryDriverPort {

    ApparelCategoriesResponse getApparelCategoriesByGenderIdAndBranCategoryId(int genderId, int brandId);
}
