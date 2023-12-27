package com.ecommerce.port.drivers;

import com.ecommerce.model.address.Address;
import com.ecommerce.model.address.AddressResponse;

public interface AddressDriverPort {

    Address saveAddress(Address address);

    Address updateAddress(Long id, Address address);

    Address fetchAddress(Long id);

    AddressResponse getUserAddress();

    void deleteAddress(Long id);

}
