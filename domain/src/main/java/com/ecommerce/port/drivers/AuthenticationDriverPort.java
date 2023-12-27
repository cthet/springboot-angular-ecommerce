package com.ecommerce.port.drivers;

import com.ecommerce.model.auth.AuthRequest;
import com.ecommerce.model.auth.AuthResponse;
import com.ecommerce.model.auth.SignupRequest;

public interface AuthenticationDriverPort {
    AuthResponse signIn(AuthRequest authRequest);

    void signup(SignupRequest signupRequest);
}
