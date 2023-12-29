package com.ecommerce.repository.springdata.adapters;

import com.ecommerce.exception.CivilityNotFound;
import com.ecommerce.model.user.Civility;
import com.ecommerce.port.adapters.repositories.CivilityRepositoryPort;
import com.ecommerce.repository.springdata.entity.CivilityEntity;
import com.ecommerce.repository.springdata.mappers.CivilityMapper;
import com.ecommerce.repository.springdata.repository.CivilityJpaRepository;
import com.ecommerce.util.message.ErrorMessages;
import org.springframework.stereotype.Repository;


@Repository
public class CivilityRepositoryJpaAdapter implements CivilityRepositoryPort {

    private final CivilityJpaRepository civilityJpaRepository;
    private CivilityMapper civilityMapper;

    public CivilityRepositoryJpaAdapter(CivilityJpaRepository civilityJpaRepository, CivilityMapper civilityMapper) {
        this.civilityJpaRepository = civilityJpaRepository;
        this.civilityMapper = civilityMapper;
    }

    @Override
    public Civility findCivilityById(int id) {
        CivilityEntity civilityEntity = civilityJpaRepository.findCivilityById(id)
                .orElseThrow(()-> new CivilityNotFound(ErrorMessages.CIVILITY_NOT_FOUND));
        return civilityMapper.toCivility(civilityEntity);
    }
}
