package com.ecommerce.repository.springdata.mappers;

import com.ecommerce.model.category.GenderCategory;
import com.ecommerce.repository.springdata.entity.GenderCategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenderCategoryMapper {

    GenderCategory toGenderCategory(GenderCategoryEntity genderCategoryEntity);

    List<GenderCategory> toGenderCategories(List<GenderCategoryEntity> genderCategoryEntities);


}
