package com.ecommerce.model.category;



import java.util.List;

public class BrandCategoriesResponse {

    private String gender;

    private List<BrandCategory> brandCategories;


    public BrandCategoriesResponse(String gender, List<BrandCategory> brandCategories) {
        this.gender = gender;
        this.brandCategories = brandCategories;
    }
}