package com.ecommerce.repository.springdata.adapters;

import com.ecommerce.model.country.Country;
import com.ecommerce.port.adapters.repositories.CountryRepositoryPort;
import com.ecommerce.repository.springdata.mappers.CountryMapper;
import com.ecommerce.repository.springdata.repository.CountryJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CountryRepositoryJpaAdapter implements CountryRepositoryPort {

    private CountryJpaRepository countryJpaRepository;
    private CountryMapper countryMapper;

    public CountryRepositoryJpaAdapter(CountryJpaRepository countryJpaRepository, CountryMapper countryMapper) {
        this.countryJpaRepository = countryJpaRepository;
        this.countryMapper = countryMapper;
    }

    @Override
    public List<Country> findAll() {
        return countryJpaRepository.findAll().stream()
                .map(countryEntity -> countryMapper.toCountry(countryEntity))
                .collect(Collectors.toList());
    }
}
