package com.ecommerce.service;

import com.ecommerce.exception.CivilityNotFound;
import com.ecommerce.model.user.Civility;
import com.ecommerce.port.drivers.CivilityDriverPort;
import com.ecommerce.port.repositories.CivilityRepositoryPort;
import com.ecommerce.util.message.ErrorMessages;

public class CivilityService implements CivilityDriverPort {

    private final CivilityRepositoryPort civilityRepository;

    public CivilityService(CivilityRepositoryPort civilityRepository) {
        this.civilityRepository = civilityRepository;
    }

    @Override
    public Civility getCivilityById(int id) {
        return civilityRepository.findCivilityById(id)
                .orElseThrow(()-> new CivilityNotFound(ErrorMessages.CIVILITY_NOT_FOUND));
    }
}
