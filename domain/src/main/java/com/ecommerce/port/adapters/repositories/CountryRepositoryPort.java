package com.ecommerce.port.adapters.repositories;

import com.ecommerce.model.country.Country;

import java.util.List;

public interface CountryRepositoryPort {
    List<Country> findAll();
}