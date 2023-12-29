package com.ecommerce.repository.springdata.mappers;


import com.ecommerce.model.cart.CartItem;
import com.ecommerce.repository.springdata.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface CartItemMapper {

    @Mapping(source = "productEntity", target = "product")
    CartItem toCartItem(CartItemEntity cartItem);

    @Mapping(source = "product", target = "productEntity")
    @Mapping(source = "cart", target = "cartEntity")
    CartItemEntity toCartItemEntity(CartItem cartItem);

}
