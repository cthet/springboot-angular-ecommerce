package com.ecommerce.repository.springdata.adapters;

import com.ecommerce.model.category.BrandCategory;
import com.ecommerce.port.adapters.repositories.BrandCategoryRepositoryPort;
import com.ecommerce.repository.springdata.entity.BrandCategoryEntity;
import com.ecommerce.repository.springdata.mappers.BrandCategoryMapper;
import com.ecommerce.repository.springdata.repository.BrandCategoryJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BrandCategoryRepositoryJpaAdapter implements BrandCategoryRepositoryPort {

    private BrandCategoryJpaRepository brandCategoryJpaRepository;
    private BrandCategoryMapper brandCategoryMapper;

    public BrandCategoryRepositoryJpaAdapter(BrandCategoryJpaRepository brandCategoryJpaRepository, BrandCategoryMapper brandCategoryMapper) {
        this.brandCategoryJpaRepository = brandCategoryJpaRepository;
        this.brandCategoryMapper = brandCategoryMapper;
    }

    @Override
    public List<BrandCategory> findByGenderCategoryIdAndProductsIsNotNull(int genderId) {
        List<BrandCategoryEntity> brandCategoryEntityList = brandCategoryJpaRepository.findByGenderCategoryIdAndProductsIsNotNull(genderId);
        return brandCategoryEntityList.stream()
                .map(brandCategoryEntity -> brandCategoryMapper.toBrandCategory(brandCategoryEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<BrandCategory> findByGenderCategoryIdAndApparelCategoryIdAndProductsIsNotNull(int genderId, int apparelCategoryId) {
        List<BrandCategoryEntity> brandCategoryEntityList = brandCategoryJpaRepository.findByGenderCategoryIdAndApparelCategoryIdAndProductsIsNotNull(genderId, apparelCategoryId);
        return brandCategoryEntityList.stream()
                .map(brandCategoryEntity -> brandCategoryMapper.toBrandCategory(brandCategoryEntity))
                .collect(Collectors.toList());
    }
}
