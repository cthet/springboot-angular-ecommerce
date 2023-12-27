package com.ecommerce.model.product;

import com.ecommerce.model.category.ApparelCategory;
import com.ecommerce.model.category.BrandCategory;
import com.ecommerce.model.category.GenderCategory;

import java.math.BigDecimal;

public class Product {

    private Long id;

    private String productName;

    private BigDecimal unitPrice;

    private String imageUrl;

    private Boolean active;

    private int unitsInStocks;

    private GenderCategory genderCategory;

    private BrandCategory brandCategory;

    private ApparelCategory apparelCategory;


    public Product(Long id, String productName, BigDecimal unitPrice, String imageUrl, Boolean active, int unitsInStocks) {
        this.id = id;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.imageUrl = imageUrl;
        this.active = active;
        this.unitsInStocks = unitsInStocks;
    }

    public void setGenderCategory(GenderCategory genderCategory) {
        this.genderCategory = genderCategory;
    }

    public void setBrandCategory(BrandCategory brandCategory) {
        this.brandCategory = brandCategory;
    }

    public void setApparelCategory(ApparelCategory apparelCategory) {
        this.apparelCategory = apparelCategory;
    }


    public Long getId() {
        return id;
    }
}
