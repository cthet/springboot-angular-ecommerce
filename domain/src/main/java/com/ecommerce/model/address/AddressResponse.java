package com.ecommerce.model.address;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddressResponse {

    List<Address> addresses;

    public AddressResponse(List<Address> addresses) {
        this.addresses = addresses;
    }
}
