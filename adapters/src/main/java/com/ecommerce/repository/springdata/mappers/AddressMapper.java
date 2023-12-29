package com.ecommerce.repository.springdata.mappers;

import com.ecommerce.model.address.Address;
import com.ecommerce.repository.springdata.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {CountryMapper.class, CivilityMapper.class, UserMapper.class, OrderMapper.class})
public interface AddressMapper {

    @Mapping(source = "countryEntity", target = "country")
    @Mapping(source = "civilityEntity", target = "civility")
    @Mapping(source = "userEntity", target = "user")
    @Mapping(source = "orderEntitySet", target = "orders")
    Address toAddress(AddressEntity addressEntity);

    @Mapping(ignore = true, target = "userEntity")
    @Mapping(ignore = true, target = "civilityEntity")
    @Mapping(ignore = true, target = "countryEntity")
    @Mapping(ignore = true, target = "orderEntitySet")
    AddressEntity toAddressEntity(Address address);

}
