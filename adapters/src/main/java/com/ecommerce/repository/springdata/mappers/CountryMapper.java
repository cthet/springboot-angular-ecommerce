package com.ecommerce.repository.springdata.mappers;

import com.ecommerce.model.country.Country;
import com.ecommerce.repository.springdata.entity.CountryEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CountryMapper {

    Country toCountry(CountryEntity countryEntity);

}
