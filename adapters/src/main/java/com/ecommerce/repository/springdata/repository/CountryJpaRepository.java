package com.ecommerce.repository.springdata.repository;

import com.ecommerce.repository.springdata.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryJpaRepository extends JpaRepository<CountryEntity, Integer> {

    List<CountryEntity> findAll();
}
