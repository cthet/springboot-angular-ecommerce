package com.ecommerce.service;

import com.ecommerce.exception.AddressAlreadyExists;
import com.ecommerce.exception.AddressNotFound;
import com.ecommerce.exception.CheckingUserAddressFailed;
import com.ecommerce.model.address.Address;
import com.ecommerce.model.address.AddressResponse;
import com.ecommerce.model.user.User;
import com.ecommerce.port.drivers.AddressDriverPort;
import com.ecommerce.port.repositories.AddressRepositoryPort;
import com.ecommerce.port.repositories.CivilityRepositoryPort;
import com.ecommerce.port.repositories.CountryRepositoryPort;
import com.ecommerce.util.message.ErrorMessages;

import java.util.ArrayList;
import java.util.List;

public class AddressService implements AddressDriverPort {

    private final UserService userService;
    private final AddressRepositoryPort addressRepository;
    private final CountryRepositoryPort countryRepository;
    private final CivilityRepositoryPort civilityRepository;

    public AddressService(UserService userService, AddressRepositoryPort addressRepository, CountryRepositoryPort countryRepository, CivilityRepositoryPort civilityRepository) {
        this.userService = userService;
        this.addressRepository = addressRepository;
        this.countryRepository = countryRepository;
        this.civilityRepository = civilityRepository;
    }

    @Override
    public Address fetchAddress(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFound(ErrorMessages.ADDRESS_NOT_FOUND));
    }

    @Override
    public Address saveAddress(Address address) {
        checkAddressExistence(address.getId());
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Long id, Address address) {
        Address addressFromDB = fetchAddress(id);
        checkUserAddress(addressFromDB);
        return addressRepository.save(address);
    }

    @Override
    public AddressResponse getUserAddress() {
        User user = userService.getUser();
        List<Address> addresses = addressRepository.findByUserId(user.getId());

        if (addresses.isEmpty()) {
            return new AddressResponse(new ArrayList<Address>());
        }
        return new AddressResponse(addresses);
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = fetchAddress(id);

        this.checkUserAddress(address);

        addressRepository.deleteById(id);
    }

    private void checkAddressExistence(Long id) {
        addressRepository.findById(id).ifPresent(s -> {
            throw new AddressAlreadyExists(ErrorMessages.ADDRESS_ALREADY_EXISTS);
        });
    }
    private void checkUserAddress(Address address){
        User user = userService.getUser();
        if(user != address.getUser()){
            throw new CheckingUserAddressFailed(ErrorMessages.CHECKING_USER_ADDRESS_FAILED);
        }
    }

}
