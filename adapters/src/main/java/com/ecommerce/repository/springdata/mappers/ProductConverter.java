//package com.ecommerce.repository.springdata.mappers;
//
//import com.ecommerce.model.product.Product;
//import com.ecommerce.repository.springdata.entity.ProductEntity;
//
//public class ProductConverter {
//
//    public static Product toProduct(ProductEntity productEntity) {
//        if (productEntity == null) {
//            return null;
//        }
//
//        Product product = new Product(
//                productEntity.getId(),
//                productEntity.getProductName(),
//                productEntity.getUnitPrice(),
//                productEntity.getImageUrl(),
//                productEntity.getActive(),
//                productEntity.getUnitsInStocks()
//        );
//
//        // Assuming you have converters for the category entities
//        product.setGenderCategory(GenderCategoryConverter.toGenderCategory(productEntity.getGenderCategoryEntity()));
//        product.setBrandCategory(BrandCategoryConverter.toBrandCategory(productEntity.getBrandCategoryEntity()));
//        product.setApparelCategory(ApparelCategoryConverter.toApparelCategory(productEntity.getApparelCategory()));
//
//        return product;
//    }
//
//
//    public static ProductEntity toProductEntity(Product product) {
//        if (product == null) {
//            return null;
//        }
//
//        ProductEntity productEntity = new ProductEntity();
//        productEntity.setId(product.getId());
//        productEntity.setProductName(product.getProductName());
//        productEntity.setUnitPrice(product.getUnitPrice());
//        productEntity.setImageUrl(product.getImageUrl());
//        productEntity.setActive(product.getActive());
//        productEntity.setUnitsInStocks(product.getUnitsInStocks());
//
//        // Assuming you have converters for the category entities
//        productEntity.setGenderCategoryEntity(GenderCategoryConverter.toGenderCategoryEntity(product.getGenderCategory()));
//        productEntity.setBrandCategoryEntity(BrandCategoryConverter.toBrandCategoryEntity(product.getBrandCategory()));
//        productEntity.setApparelCategoryEntity(ApparelCategoryConverter.toApparelCategoryEntity(product.getApparelCategory()));
//
//        return productEntity;
//    }
//}
