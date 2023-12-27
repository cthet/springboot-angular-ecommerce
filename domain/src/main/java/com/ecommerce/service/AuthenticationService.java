package com.ecommerce.service;

import com.ecommerce.exception.*;
import com.ecommerce.model.auth.AuthRequest;
import com.ecommerce.model.auth.AuthResponse;
import com.ecommerce.model.auth.SignupRequest;
import com.ecommerce.model.auth.UserDto;
import com.ecommerce.model.user.Civility;
import com.ecommerce.model.user.User;
import com.ecommerce.port.drivers.AuthenticationDriverPort;
import com.ecommerce.port.repositories.CivilityRepositoryPort;
import com.ecommerce.port.repositories.UserRepositoryPort;
import com.ecommerce.security.JwtUtils;
import com.ecommerce.security.UserDetailsImpl;
import com.ecommerce.util.enums.Role;
import com.ecommerce.util.message.ErrorMessages;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.stream.Collectors;

public class AuthenticationService implements AuthenticationDriverPort {

    private final AuthenticationManager authenticationManager;
    private final UserRepositoryPort userRepository;
    private final CivilityRepositoryPort civilityRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public AuthenticationService(AuthenticationManager authenticationManager, UserRepositoryPort userRepository, CivilityRepositoryPort civilityRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.civilityRepository = civilityRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }


    @Override
    public AuthResponse signIn(AuthRequest authRequest) {
        checkUserExistsInDatabase(authRequest.getEmail());

        UserDetailsImpl user = authenticateUser(authRequest.getEmail(), authRequest.getPassword());

        Long id = user.getId();
        String email = user.getEmail();
        String role = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        UserDto userDTO = new UserDto(id, role);

        String jwt = jwtUtils.generateToken(email, role);

        return new AuthResponse(userDTO, jwt);
    }

    public void signup(SignupRequest signupRequest) {

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new EmailAlreadyExists(ErrorMessages.EMAIL_ALREADY_EXISTS);
        }

        createUser(signupRequest);
    }

    private UserDetailsImpl authenticateUser(String email, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return (UserDetailsImpl) authentication.getPrincipal();

        } catch(AuthenticationException e) {
            throw  new InvalidCredentials(ErrorMessages.INVALID_CREDENTIALS);
        }
    }

    private void checkUserExistsInDatabase(String email){
        if(email == null){
            throw new EmailIsNull(ErrorMessages.EMAIL_IS_NULL);
        }

        if(!userRepository.existsByEmail(email)){
            throw new EmailNotFound(ErrorMessages.EMAIL_NOT_FOUND);
        }
    }

    private void createUser(SignupRequest signupRequest) {
        User user = signupRequest.signupRequestToUser(signupRequest);
        user.setRole(Collections.singleton(Role.USER));
        user.setPassword(encoder.encode(signupRequest.getPassword()));

        Civility civility = civilityRepository.findCivilityById(signupRequest.getCivility())
                .orElseThrow(() -> new CivilityNotFound(ErrorMessages.CIVILITY_NOT_FOUND));

        user.setCivility(civility);

        userRepository.save(user);
    }
}
