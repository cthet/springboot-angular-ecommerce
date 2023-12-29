package com.ecommerce.repository.springdata.mappers;

import com.ecommerce.model.category.BrandCategory;
import com.ecommerce.repository.springdata.entity.BrandCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandCategoryMapper {

    @Mapping(source = "id", target = "id")
    BrandCategory toBrandCategory(BrandCategoryEntity brandCategoryEntity);

}
