package com.ecommerce.model.country;


import java.util.List;


public class CountriesResponse {


    private List<Country> countries;

    public CountriesResponse(List<Country> countries) {
        this.countries = countries;
    }
}
