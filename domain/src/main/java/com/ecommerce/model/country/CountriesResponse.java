package com.ecommerce.model.country;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CountriesResponse {


    private List<Country> countries;

    public CountriesResponse(List<Country> countries) {
        this.countries = countries;
    }
}
