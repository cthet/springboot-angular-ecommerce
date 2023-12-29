package com.ecommerce.repository.springdata.repository;

import com.ecommerce.repository.springdata.entity.BrandCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandCategoryJpaRepository extends JpaRepository<BrandCategoryEntity, Integer> {

    @Query("SELECT DISTINCT b FROM BrandCategoryEntity b" +
            " JOIN b.products p" +
            " JOIN b.genderCategoryEntitySet g" +
            " WHERE g.id = :genderId" +
            " AND p.genderCategory.id = :genderId" +
            " GROUP BY b" +
            " HAVING COUNT(p) > 0")
    List<BrandCategoryEntity> findByGenderCategoryIdAndProductsIsNotNull(@Param("genderId") int genderId);

    @Query("SELECT DISTINCT b FROM BrandCategoryEntity b" +
            " JOIN b.products p" +
            " JOIN b.apparelCategoryEntitySet a" +
            " JOIN b.genderCategoryEntitySet g" +
            " WHERE a.id = :apparelCategoryId" +
            " AND g.id = :genderId" +
            " AND p.genderCategory.id = :genderId" +
            " AND p.apparelCategory.id = :apparelCategoryId" +
            " GROUP BY b" +
            " HAVING COUNT(p) > 0")
    List<BrandCategoryEntity> findByGenderCategoryIdAndApparelCategoryIdAndProductsIsNotNull(@Param("genderId") int genderId, @Param("apparelCategoryId") int apparelCategoryId);
}
