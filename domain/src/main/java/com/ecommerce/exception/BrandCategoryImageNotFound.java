package com.ecommerce.exception;

public class BrandCategoryImageNotFound extends RuntimeException{
    public BrandCategoryImageNotFound(String message, int brandCategoryId) {
        super(message);
    }
}
