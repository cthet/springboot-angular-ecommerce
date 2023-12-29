package com.ecommerce.model.category;



import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BrandCategoriesResponse {

    private String gender;

    private List<BrandCategory> brandCategories;


    public BrandCategoriesResponse(String gender, List<BrandCategory> brandCategories) {
        this.gender = gender;
        this.brandCategories = brandCategories;
    }
}