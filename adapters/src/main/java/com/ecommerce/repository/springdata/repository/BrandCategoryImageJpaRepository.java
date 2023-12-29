package com.ecommerce.repository.springdata.repository;

import com.ecommerce.repository.springdata.entity.BrandCategoryImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandCategoryImageJpaRepository extends JpaRepository<BrandCategoryImageEntity, Integer> {

    Optional<BrandCategoryImageEntity> findByBrandCategoryIdAndGenderCategoryId(int brandId, int gender);
}
