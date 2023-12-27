package com.ecommerce.port.repositories;

import com.ecommerce.model.address.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepositoryPort {

    Optional<Address> findById(Long id);

    List<Address> findByUserId(long userId);

    Address save(Address address);

    void deleteById(Long id);
}
