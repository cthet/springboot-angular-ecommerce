package com.ecommerce.model.auth;

import com.ecommerce.model.user.User;

public class SignupRequest {

    private int civility;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User signupRequestToUser(SignupRequest signup) {
        if ( signup == null ) {
            return null;
        }

        User user = new User();

        user.setFirstName( signup.getFirstName() );
        user.setLastName( signup.getLastName() );
        user.setEmail( signup.getEmail() );

        return user;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCivility() {
        return civility;
    }

    public SignupRequest(int civility, String firstName, String lastName, String email, String password) {
        this.civility = civility;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
