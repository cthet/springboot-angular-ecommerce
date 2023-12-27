package com.ecommerce.service;


import com.ecommerce.model.country.CountriesResponse;
import com.ecommerce.model.country.Country;
import com.ecommerce.port.drivers.CountryDriverPort;
import com.ecommerce.port.repositories.CountryRepositoryPort;

import java.util.List;

public class CountryService implements CountryDriverPort {

    private final CountryRepositoryPort countryRepository;

    public CountryService(CountryRepositoryPort countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public CountriesResponse fetchCountries() {

        List<Country> countries = countryRepository.findAll();

        CountriesResponse countriesResponse = new CountriesResponse(countries);

        return countriesResponse;
    }
}
