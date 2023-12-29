package com.ecommerce.repository.springdata.repository;

import com.ecommerce.repository.springdata.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, Long> {


    void deleteById(Long Id);
}
