package com.ecommerce.repository.springdata.repository;

import com.ecommerce.repository.springdata.entity.ApparelCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApparelCategoryJpaRepository extends JpaRepository<ApparelCategoryEntity, Integer> {

    List<ApparelCategoryEntity> findAll();

    @Query("SELECT DISTINCT a FROM ApparelCategoryEntity a" +
            " JOIN a.products p"+
            " JOIN a.genderCategoryEntitySet g" +
            " WHERE p.genderCategory.id = :genderId" +
            " AND g.id =:genderId"+
            " GROUP BY a" +
            " HAVING COUNT(p) > 0")
    List<ApparelCategoryEntity> findByGenderCategoryIdAndProductsIsNotNull(@Param("genderId") int genderId);

    @Query("SELECT DISTINCT a FROM ApparelCategoryEntity a" +
            " JOIN a.products p"+
            " JOIN a.brandCategorySet b" +
            " JOIN a.genderCategoryEntitySet g" +
            " WHERE b.id = :brandId" +
            " AND g.id = :genderId"+
            " AND p.genderCategory.id = :genderId" +
            " AND p.brandCategory.id = :brandId" +
            " GROUP BY a" +
            " HAVING COUNT(p) > 0")
    List<ApparelCategoryEntity> findByBrandCategoryIdAndGenderCategoryIdAndProductsIsNotNull(@Param("brandId")int brandId, @Param("genderId") int genderId);
}