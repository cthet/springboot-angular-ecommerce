package com.ecommerce.repository.springdata.mappers;

import com.ecommerce.model.category.BrandCategoryImage;
import com.ecommerce.repository.springdata.entity.BrandCategoryImageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CountryMapper.class, CivilityMapper.class})
public interface BrandCategoryImageMapper {

    BrandCategoryImage toBrandCategoryImage(BrandCategoryImageEntity brandCategoryImageEntity);
}
