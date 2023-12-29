package com.ecommerce.port.adapters.gateway;

import com.ecommerce.model.auth.AuthRequest;
import com.ecommerce.model.auth.AuthResponse;
import com.ecommerce.model.auth.SignupRequest;
import com.ecommerce.model.user.User;

public interface AuthenticationGateway {
    User getAuthenticatedUser();

    AuthResponse signIn(AuthRequest authRequest);

    void signUp(SignupRequest signupRequest);
}
