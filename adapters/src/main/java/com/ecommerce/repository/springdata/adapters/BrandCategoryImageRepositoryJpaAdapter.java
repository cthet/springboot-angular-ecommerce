package com.ecommerce.repository.springdata.adapters;


import com.ecommerce.exception.BrandCategoryImageNotFound;
import com.ecommerce.model.category.BrandCategoryImage;
import com.ecommerce.port.adapters.repositories.BrandCategoryImageRepositoryPort;
import com.ecommerce.repository.springdata.entity.BrandCategoryImageEntity;
import com.ecommerce.repository.springdata.mappers.BrandCategoryImageMapper;
import com.ecommerce.repository.springdata.repository.BrandCategoryImageJpaRepository;
import com.ecommerce.util.message.ErrorMessages;
import org.springframework.stereotype.Repository;

@Repository
public class BrandCategoryImageRepositoryJpaAdapter implements BrandCategoryImageRepositoryPort {

    private BrandCategoryImageJpaRepository brandCategoryImageJpaRepository;
    private BrandCategoryImageMapper brandCategoryImageMapper;

    public BrandCategoryImageRepositoryJpaAdapter(BrandCategoryImageJpaRepository brandCategoryImageJpaRepository, BrandCategoryImageMapper brandCategoryImageMapper) {
        this.brandCategoryImageJpaRepository = brandCategoryImageJpaRepository;
        this.brandCategoryImageMapper = brandCategoryImageMapper;
    }

    @Override
    public BrandCategoryImage findByBrandCategoryIdAndGenderCategoryId(int brandId, int gender) {
        BrandCategoryImageEntity brandCategoryImageEntity = brandCategoryImageJpaRepository.findByBrandCategoryIdAndGenderCategoryId(brandId, gender)
                .orElseThrow(() -> new BrandCategoryImageNotFound(ErrorMessages.BRAND_CATEGORY_IMAGE_NOT_FOUND,brandId));
        return brandCategoryImageMapper.toBrandCategoryImage(brandCategoryImageEntity);
    }
}
