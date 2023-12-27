package com.ecommerce.model.category;

import com.ecommerce.model.product.Product;

import java.util.HashSet;
import java.util.Set;

public class GenderCategory {

    private int id;

    private String name;

    private Set<Product> products = new HashSet<>();

    private Set<BrandCategoryImage> brandCategoryImages = new HashSet<>();

    private Set<ApparelCategory> apparelCategories = new HashSet<>();

    private Set<BrandCategory> brandCategories = new HashSet<>();


    public String getName() {
        return name;
    }
}
