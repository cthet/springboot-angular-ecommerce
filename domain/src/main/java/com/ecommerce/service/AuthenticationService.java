package com.ecommerce.service;

import com.ecommerce.model.auth.AuthRequest;
import com.ecommerce.model.auth.AuthResponse;
import com.ecommerce.model.auth.SignupRequest;
import com.ecommerce.port.adapters.gateway.AuthenticationGateway;
import com.ecommerce.port.drivers.AuthenticationDriverPort;

public class AuthenticationService implements AuthenticationDriverPort {

    private final AuthenticationGateway authenticationGateway;

    public AuthenticationService(AuthenticationGateway authenticationGateway) {
        this.authenticationGateway = authenticationGateway;
    }

    @Override
    public AuthResponse signIn(AuthRequest authRequest) {
        return authenticationGateway.signIn(authRequest);
    }

    @Override
    public void signup(SignupRequest signupRequest) {
        authenticationGateway.signUp(signupRequest);
    }



}
