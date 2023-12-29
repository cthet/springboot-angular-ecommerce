package com.ecommerce.repository.springdata.adapters;

import com.ecommerce.exception.AddressNotFound;
import com.ecommerce.exception.CivilityNotFound;
import com.ecommerce.exception.CountryNotFound;
import com.ecommerce.exception.UserNotFound;
import com.ecommerce.model.address.Address;
import com.ecommerce.model.order.Order;
import com.ecommerce.model.user.User;
import com.ecommerce.port.adapters.repositories.AddressRepositoryPort;
import com.ecommerce.repository.springdata.entity.*;
import com.ecommerce.repository.springdata.mappers.AddressMapper;
import com.ecommerce.repository.springdata.mappers.OrderMapper;
import com.ecommerce.repository.springdata.repository.AddressJpaRepository;
import com.ecommerce.repository.springdata.repository.CivilityJpaRepository;
import com.ecommerce.repository.springdata.repository.CountryJpaRepository;
import com.ecommerce.repository.springdata.repository.UserJpaRepository;
import com.ecommerce.util.message.ErrorMessages;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class AddressRepositoryJpaAdapter implements AddressRepositoryPort {
    private AddressJpaRepository addressJpaRepository;
    private AddressMapper addressMapper;
    private CivilityJpaRepository civilityJpaRepository;
    private CountryJpaRepository countryJpaRepository;
    private UserJpaRepository userJpaRepository;
    private OrderMapper orderMapper;
    private OrderRepositoryJpaAdapter orderRepositoryJpaAdapter;


    public AddressRepositoryJpaAdapter(AddressJpaRepository addressJpaRepository, AddressMapper addressMapper, CivilityJpaRepository cvilityJpaRepository, CountryJpaRepository countryJpaRepository, UserJpaRepository userJpaRepository, OrderMapper orderMapper, OrderRepositoryJpaAdapter orderRepositoryJpaAdapter) {
        this.addressJpaRepository = addressJpaRepository;
        this.addressMapper = addressMapper;
        this.civilityJpaRepository = cvilityJpaRepository;
        this.countryJpaRepository = countryJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.orderMapper = orderMapper;
        this.orderRepositoryJpaAdapter = orderRepositoryJpaAdapter;
    }

    @Override
    public Address findById(Long id) {
        AddressEntity addressEntity = addressJpaRepository.findById(id)
                .orElseThrow(() -> new AddressNotFound(ErrorMessages.ADDRESS_NOT_FOUND));
        return addressMapper.toAddress(addressEntity);
    }

    @Override
    public void deleteById(Long id) {
        addressJpaRepository.deleteById(id);
    }

    @Override
    public List<Address> findByUserId(long userId) {
        List<AddressEntity> addressEntityList = addressJpaRepository.findByUserId(userId);
        return addressEntityList.stream()
                .map(addressEntity -> addressMapper.toAddress(addressEntity))
                .collect(Collectors.toList());
    }

    @Override
    public Address save(Address address) {
        AddressEntity addressEntity = mapAddressToAddressEntity(address);
        AddressEntity savedAddressEntity = addressJpaRepository.save(addressEntity);
        return addressMapper.toAddress(savedAddressEntity);
    }

    private AddressEntity mapAddressToAddressEntity(Address address) {
        AddressEntity addressEntity = addressMapper.toAddressEntity(address);
        setCivilityEntity(addressEntity, address);
        setCountryEntity(addressEntity, address);
        setUserEntity(addressEntity, address.getUser());
        setOrderEntitySet(addressEntity, address.getOrders());
        return  addressEntity;
    }

    private void setOrderEntitySet(AddressEntity addressEntity, Set<Order> orders) {
        Set<OrderEntity> orderEntitySet = orders.stream()
                .map(order -> {
                    OrderEntity orderEntity = orderMapper.toOrderEntity(order);
                    orderEntity.setAddressEntity(addressEntity);
                    orderEntity.setUserEntity(addressEntity.getUserEntity());
                    orderRepositoryJpaAdapter.setOrderItemEntities(orderEntity, order.getOrderItems());
                    return orderEntity;
                })
                .collect(Collectors.toSet());
        addressEntity.setOrderEntitySet(orderEntitySet);
    }

    private void setCountryEntity(AddressEntity addressEntity, Address address) {
        CountryEntity countryEntity = countryJpaRepository.findById(address.getCountry().getId())
                .orElseThrow(()-> new CountryNotFound(ErrorMessages.COUNTRY_NOT_FOUND));
        addressEntity.setCountryEntity(countryEntity);
    }

    private void setCivilityEntity(AddressEntity addressEntity, Address address) {
        CivilityEntity civilityEntity = civilityJpaRepository.findCivilityById(address.getCivility().getId()).orElseThrow(()-> new CivilityNotFound(ErrorMessages.CIVILITY_NOT_FOUND));
        addressEntity.setCivilityEntity(civilityEntity);
    }

    private void setUserEntity(AddressEntity addressEntity, User user) {
        UserEntity userEntity = userJpaRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFound(ErrorMessages.USER_NOT_FOUND));
        addressEntity.setUserEntity(userEntity);
    }

}
