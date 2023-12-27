package com.ecommerce.port.repositories;

import com.ecommerce.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    Page<Product> findByGenderCategoryId(int gender, Pageable pageable);

    Page<Product> findByGenderCategoryIdAndBrandCategoryIdIn(int gender, List<Integer> brand, Pageable pageable);

    Page<Product> findByGenderCategoryIdAndApparelCategoryIdIn(int gender, List<Integer> category, Pageable pageable);

    Page<Product> findByGenderCategoryIdAndAndBrandCategoryIdInApparelCategoryIdIn(int gender, List<Integer> brand, List<Integer> category,  Pageable pageable);


    Page<Product> findNewProductByGenderCategoryId(int genderId, Pageable pageable);

    Optional<Product> findById(Long productId);
}