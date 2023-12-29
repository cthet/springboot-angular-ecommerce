//package com.ecommerce.repository.springdata.mappers;
//
//import com.ecommerce.model.country.Country;
//import com.ecommerce.repository.springdata.entity.CountryEntity;
//
//public class CountryConverter {
//
//    public static Country toCountry(CountryEntity countryEntity) {
//        if (countryEntity == null) {
//            return null;
//        }
//
//        Country country = new Country();
//        country.setId(countryEntity.getId());
//        country.setName(countryEntity.getName());
//        country.setCode(countryEntity.getCode());
//
//        return country;
//    }
//
//    public static CountryEntity toCountryEntity(Country country) {
//        if (country == null) {
//            return null;
//        }
//
//        CountryEntity countryEntity = new CountryEntity();
//        countryEntity.setId(country.getId());
//        countryEntity.setName(country.getName());
//        countryEntity.setCode(country.getCode());
//
//        return countryEntity;
//    }
//}
