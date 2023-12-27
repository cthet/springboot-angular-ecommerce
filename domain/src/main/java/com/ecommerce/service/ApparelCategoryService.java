package com.ecommerce.service;

import com.ecommerce.exception.ApparelCategoriesNotFound;
import com.ecommerce.exception.GenderNotFound;
import com.ecommerce.model.category.ApparelCategoriesResponse;
import com.ecommerce.model.category.ApparelCategory;
import com.ecommerce.model.category.GenderCategory;
import com.ecommerce.port.drivers.ApparelCategoryDriverPort;
import com.ecommerce.port.repositories.ApparelCategoryRepositoryPort;
import com.ecommerce.port.repositories.GenderCategoryRepositoryPort;
import com.ecommerce.util.message.ErrorMessages;

import java.util.List;

public class ApparelCategoryService implements ApparelCategoryDriverPort {

    private final ApparelCategoryRepositoryPort apparelCategoryRepository;
    private final GenderCategoryRepositoryPort genderCategoryRepository;

    public ApparelCategoryService(ApparelCategoryRepositoryPort apparelCategoryRepository, GenderCategoryRepositoryPort genderCategoryRepository) {
        this.apparelCategoryRepository = apparelCategoryRepository;
        this.genderCategoryRepository = genderCategoryRepository;
    }


    @Override
    public ApparelCategoriesResponse getApparelCategoriesByGenderIdAndBranCategoryId(int genderId, int brandId) {

        GenderCategory gender = checkGender(genderId);

        ApparelCategoriesResponse apparelCategoriesResponse = new ApparelCategoriesResponse();
        apparelCategoriesResponse.setGender(gender.getName());
        apparelCategoriesResponse.setApparelCategories(getApparelCategories(brandId, genderId));

        return apparelCategoriesResponse;

    }

    private GenderCategory checkGender(int genderId) {
        GenderCategory gender = genderCategoryRepository.findById(genderId)
                .orElseThrow(() -> new GenderNotFound(ErrorMessages.GENDER_NOT_FOUND, genderId));
        return gender;
    }

    private List<ApparelCategory> getApparelCategories(int brand, int gender) {
        List<ApparelCategory> apparelCategories;

        if (brand == 0) {
            apparelCategories = getAllApparelCategoriesGivenAGender(gender);
        } else {
            apparelCategories = getAllApparelCategoriesGivenAGenderAndABrand(brand, gender);
        }

        checkApparelCategoriesIsNotEmpty(apparelCategories);

        return apparelCategories;
    }

    private List<ApparelCategory> getAllApparelCategoriesGivenAGender(int gender) {
        List<ApparelCategory> apparelCategories;
        apparelCategories = apparelCategoryRepository.findByGenderCategoryIdAndProductsIsNotNull(gender);
        return apparelCategories;
    }

    private List<ApparelCategory> getAllApparelCategoriesGivenAGenderAndABrand(int brand, int gender) {
        List<ApparelCategory> apparelCategories;
        apparelCategories = apparelCategoryRepository.findByBrandCategoryIdAndGenderCategoryIdAndProductsIsNotNull(brand, gender);
        return apparelCategories;
    }

    private void checkApparelCategoriesIsNotEmpty(List<ApparelCategory> apparelCategories) {
        if (apparelCategories.isEmpty()) {
            throw new ApparelCategoriesNotFound(ErrorMessages.APPAREL_CATEGORIES_NOT_FOUND);
        }
    }

}


