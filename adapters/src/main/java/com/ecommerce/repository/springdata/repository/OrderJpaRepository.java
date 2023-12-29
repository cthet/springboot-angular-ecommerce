package com.ecommerce.repository.springdata.repository;

import com.ecommerce.repository.springdata.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findOrderByUserId(long id);
}
