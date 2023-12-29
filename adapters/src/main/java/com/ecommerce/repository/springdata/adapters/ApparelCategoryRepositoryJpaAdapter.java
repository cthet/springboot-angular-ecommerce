package com.ecommerce.repository.springdata.adapters;

import com.ecommerce.model.category.ApparelCategory;
import com.ecommerce.port.adapters.repositories.ApparelCategoryRepositoryPort;
import com.ecommerce.repository.springdata.entity.ApparelCategoryEntity;
import com.ecommerce.repository.springdata.mappers.ApparelCategoryMapper;
import com.ecommerce.repository.springdata.repository.ApparelCategoryJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ApparelCategoryRepositoryJpaAdapter implements ApparelCategoryRepositoryPort {

    private ApparelCategoryJpaRepository apparelCategoryJpaRepository;
    private ApparelCategoryMapper apparelCategoryMapper;

    public ApparelCategoryRepositoryJpaAdapter(ApparelCategoryJpaRepository apparelCategoryJpaRepository, ApparelCategoryMapper apparelCategoryMapper) {
        this.apparelCategoryJpaRepository = apparelCategoryJpaRepository;
        this.apparelCategoryMapper = apparelCategoryMapper;
    }

    @Override
    public List<ApparelCategory> findAll() {
        List<ApparelCategoryEntity> apparelCategoryEntityList = apparelCategoryJpaRepository.findAll();
        return apparelCategoryEntityList.stream()
                .map(apparelCategoryEntity -> apparelCategoryMapper.toApparelCategory(apparelCategoryEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApparelCategory> findByGenderCategoryIdAndProductsIsNotNull(int genderId) {
        List<ApparelCategoryEntity> apparelCategoryEntityList = apparelCategoryJpaRepository.findByGenderCategoryIdAndProductsIsNotNull(genderId);
        return apparelCategoryEntityList.stream()
                .map(apparelCategoryEntity -> apparelCategoryMapper.toApparelCategory(apparelCategoryEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApparelCategory> findByBrandCategoryIdAndGenderCategoryIdAndProductsIsNotNull(int brandId, int genderId) {
        List<ApparelCategoryEntity> apparelCategoryEntityList = apparelCategoryJpaRepository.findByBrandCategoryIdAndGenderCategoryIdAndProductsIsNotNull(brandId, genderId);
        return apparelCategoryEntityList.stream()
                .map(apparelCategoryEntity -> apparelCategoryMapper.toApparelCategory(apparelCategoryEntity))
                .collect(Collectors.toList());
    }
}