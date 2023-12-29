package com.ecommerce.repository.springdata.mappers;

import com.ecommerce.model.category.ApparelCategory;
import com.ecommerce.repository.springdata.entity.ApparelCategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApparelCategoryMapper {

    ApparelCategory toApparelCategory(ApparelCategoryEntity apparelCategoryEntity);


}
