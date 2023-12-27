package com.ecommerce.model.address;

import com.ecommerce.model.order.Order;
import com.ecommerce.model.user.Civility;
import com.ecommerce.model.country.Country;
import com.ecommerce.model.user.User;

import java.util.HashSet;
import java.util.Set;

public class Address {

    private Long id;

    private Civility civility;

    private String firstName;

    private String lastName;

    private String street;

    private String addressComplement;

    private String city;

    private int postCode;

    private Country country;

    private String phoneNumber;

    private User user;

    private Set<Order> orders = new HashSet<>();

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}