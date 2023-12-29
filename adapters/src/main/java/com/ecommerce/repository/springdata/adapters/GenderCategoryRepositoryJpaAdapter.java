package com.ecommerce.repository.springdata.adapters;

import com.ecommerce.exception.GenderNotFound;
import com.ecommerce.model.category.GenderCategory;
import com.ecommerce.port.adapters.repositories.GenderCategoryRepositoryPort;
import com.ecommerce.repository.springdata.entity.GenderCategoryEntity;
import com.ecommerce.repository.springdata.mappers.GenderCategoryMapper;
import com.ecommerce.repository.springdata.repository.GenderCategoryJpaRepository;
import com.ecommerce.util.message.ErrorMessages;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class GenderCategoryRepositoryJpaAdapter implements GenderCategoryRepositoryPort {

    private GenderCategoryJpaRepository genderCategoryJpaRepository;
    private GenderCategoryMapper genderCategoryMapper;

    public GenderCategoryRepositoryJpaAdapter(GenderCategoryJpaRepository genderCategoryJpaRepository, GenderCategoryMapper genderCategoryMapper) {
        this.genderCategoryJpaRepository = genderCategoryJpaRepository;
        this.genderCategoryMapper = genderCategoryMapper;
    }

    @Override
    public List<GenderCategory> findAll() {
        return genderCategoryJpaRepository.findAll().stream()
                .map(genderCategoryEntity -> genderCategoryMapper.toGenderCategory(genderCategoryEntity))
                .collect(Collectors.toList());
    }

    @Override
    public GenderCategory findById(int gender) {
        GenderCategoryEntity genderCategoryEntity = genderCategoryJpaRepository.findById(gender)
                .orElseThrow(()-> new GenderNotFound(ErrorMessages.GENDER_NOT_FOUND));
        return genderCategoryMapper.toGenderCategory(genderCategoryEntity);
    }
}
