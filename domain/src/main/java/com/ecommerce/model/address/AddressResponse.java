package com.ecommerce.model.address;


import java.util.List;

public class AddressResponse {

    List<Address> addresses;

    public AddressResponse(List<Address> addresses) {
        this.addresses = addresses;
    }
}
