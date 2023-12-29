package com.ecommerce.service;

import com.ecommerce.model.user.Civility;
import com.ecommerce.port.drivers.CivilityDriverPort;
import com.ecommerce.port.adapters.repositories.CivilityRepositoryPort;

public class CivilityService implements CivilityDriverPort {

    private final CivilityRepositoryPort civilityRepository;

    public CivilityService(CivilityRepositoryPort civilityRepository) {
        this.civilityRepository = civilityRepository;
    }

    @Override
    public Civility getCivilityById(int id) {
        return civilityRepository.findCivilityById(id);
    }
}
