package com.ecommerce.repository.springdata.mappers;

import com.ecommerce.model.product.Product;
import com.ecommerce.repository.springdata.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GenderCategoryMapper.class, ApparelCategoryMapper.class})
public interface ProductMapper {

    @Mapping(source = "genderCategoryEntity", target = "genderCategory")
    @Mapping(source = "apparelCategoryEntity", target = "apparelCategory")
    @Mapping(source = "brandCategoryEntity", target = "brandCategory")
    Product productEntityToProduct(ProductEntity productEntity);

    List<Product> productsToProductsDto(List<ProductEntity> productEntities);
}
