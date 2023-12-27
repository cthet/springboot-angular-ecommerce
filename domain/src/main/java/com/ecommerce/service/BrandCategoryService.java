package com.ecommerce.service;


import com.ecommerce.exception.BrandCategoriesNotFound;
import com.ecommerce.exception.BrandCategoryImageNotFound;
import com.ecommerce.exception.GenderNotFound;
import com.ecommerce.model.category.BrandCategoriesResponse;
import com.ecommerce.model.category.BrandCategory;
import com.ecommerce.model.category.BrandCategoryImage;
import com.ecommerce.model.category.GenderCategory;
import com.ecommerce.port.drivers.BrandCategoryDriverPort;
import com.ecommerce.port.repositories.BrandCategoryImageRepositoryPort;
import com.ecommerce.port.repositories.BrandCategoryRepositoryPort;
import com.ecommerce.port.repositories.GenderCategoryRepositoryPort;
import com.ecommerce.util.message.ErrorMessages;

import java.util.List;
import java.util.stream.Collectors;


public class BrandCategoryService implements BrandCategoryDriverPort {

    private final BrandCategoryRepositoryPort brandCategoryRepository;
    private final BrandCategoryImageRepositoryPort brandCategoryImageRepository;
    private final GenderCategoryRepositoryPort genderCategoryRepository;

    public BrandCategoryService(BrandCategoryRepositoryPort brandCategoryRepository, BrandCategoryImageRepositoryPort brandCategoryImageRepository, GenderCategoryRepositoryPort genderCategoryRepository) {
        this.brandCategoryRepository = brandCategoryRepository;
        this.brandCategoryImageRepository = brandCategoryImageRepository;
        this.genderCategoryRepository = genderCategoryRepository;
    }


    @Override
    public BrandCategoriesResponse getBrandCategoriesByGenderIdAndApparelCategoryId(int genderId, int apparelCategoryId) {

        String genderName = getGenderName(genderId);

        List<BrandCategory> brandCategories = getBrandCategories(genderId, apparelCategoryId);

        checkBrandCategoriesNotEmpty(brandCategories);

        List<BrandCategory> updatedBrandCategory = setImageUrlToBrandCategories(genderId, brandCategories);

        return new BrandCategoriesResponse(genderName, updatedBrandCategory);
    }

    private String getGenderName(int genderId) {
        return genderCategoryRepository.findById(genderId)
                .map(GenderCategory::getName)
                .orElseThrow(() -> new GenderNotFound(ErrorMessages.GENDER_NOT_FOUND, genderId));
    }

    private List<BrandCategory> getBrandCategories(int genderId, int apparelCategoryId) {
        if(apparelCategoryId == 0)
            return brandCategoryRepository.findByGenderCategoryIdAndProductsIsNotNull(genderId);
        return brandCategoryRepository.findByGenderCategoryIdAndApparelCategoryIdAndProductsIsNotNull(genderId, apparelCategoryId);
    }

    private void checkBrandCategoriesNotEmpty(List<BrandCategory> brandCategories) {
        if (brandCategories.isEmpty()){
            throw new BrandCategoriesNotFound(ErrorMessages.BRAND_CATEGORIES_NOT_FOUND);
        }
    }

    private List<BrandCategory> setImageUrlToBrandCategories(int genderId, List<BrandCategory> brandCategories) {
        return brandCategories.stream()
                .map(brandCategory -> setImageUrlToBrandCategory(genderId, brandCategory))
                .collect(Collectors.toList());
    }

    private BrandCategory setImageUrlToBrandCategory(int genderId, BrandCategory brandCategory) {
        String imageUrl = brandCategoryImageRepository.findByBrandCategoryIdAndGenderCategoryId(brandCategory.getId(), genderId)
                .map(BrandCategoryImage::getImage_url)
                .orElseThrow(() -> new BrandCategoryImageNotFound(ErrorMessages.BRAND_CATEGORY_IMAGE_NOT_FOUND,brandCategory.getId()));

        brandCategory.setImageUrl(imageUrl);
        return brandCategory;
    }




}
