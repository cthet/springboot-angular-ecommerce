//package com.ecommerce.repository.springdata.mappers;
//
//import com.ecommerce.model.category.ApparelCategory;
//import com.ecommerce.repository.springdata.entity.ApparelCategoryEntity;
//
//public class ApparelCategoryConverter {
//
//    public static ApparelCategory toApparelCategory(ApparelCategoryEntity entity) {
//        if (entity == null) {
//            return null;
//        }
//
//        ApparelCategory category = new ApparelCategory();
//        category.setId(entity.getId());
//        category.setName(entity.getName());
//        // Assuming that you have converters for related entities
//        // Convert and set other fields if necessary
//
//        return category;
//    }
//
//    public static ApparelCategoryEntity toApparelCategoryEntity(ApparelCategory category) {
//        if (category == null) {
//            return null;
//        }
//
//        ApparelCategoryEntity entity = new ApparelCategoryEntity();
//        entity.setId(category.getId());
//        entity.setName(category.getName());
//        // Convert and set other fields if necessary
//
//        return entity;
//    }
//}
