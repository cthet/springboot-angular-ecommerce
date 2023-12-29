package com.ecommerce.repository.springdata.repository;

import com.ecommerce.repository.springdata.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {

    Optional<AddressEntity> findById(Long id);

    List<AddressEntity> findByUserId(long userId);
}
