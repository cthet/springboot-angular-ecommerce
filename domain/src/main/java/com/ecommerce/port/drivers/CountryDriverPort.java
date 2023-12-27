package com.ecommerce.port.drivers;

import com.ecommerce.model.country.CountriesResponse;

public interface CountryDriverPort {

    CountriesResponse fetchCountries();
}
