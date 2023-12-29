package com.ecommerce.repository.springdata.mappers;

import com.ecommerce.model.user.Civility;
import com.ecommerce.repository.springdata.entity.CivilityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CivilityMapper {


    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Civility toCivility(CivilityEntity civilityEntity);

}
