package com.ecommerce.repository.springdata.repository;


import com.ecommerce.repository.springdata.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartJpaRepository extends JpaRepository<CartEntity, Long> {


    Optional<CartEntity> findCartByUserId(long id);

}
