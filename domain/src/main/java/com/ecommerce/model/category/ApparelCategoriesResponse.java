package com.ecommerce.model.category;


import java.util.List;


public class ApparelCategoriesResponse {

    private String gender;

    private List<ApparelCategory> apparelCategories;

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setApparelCategories(List<ApparelCategory> apparelCategories) {
        this.apparelCategories = apparelCategories;
    }
}
