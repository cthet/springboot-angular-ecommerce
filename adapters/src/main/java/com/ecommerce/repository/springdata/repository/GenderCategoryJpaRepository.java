package com.ecommerce.repository.springdata.repository;

import com.ecommerce.repository.springdata.entity.GenderCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenderCategoryJpaRepository extends JpaRepository<GenderCategoryEntity, Integer> {
    List<GenderCategoryEntity> findAll();

}
