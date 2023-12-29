package com.ecommerce.service;

import com.ecommerce.model.message.MessageResponse;
import com.ecommerce.model.user.Email;
import com.ecommerce.model.user.Info;
import com.ecommerce.model.user.Profile;
import com.ecommerce.model.user.User;
import com.ecommerce.port.drivers.UserDriverPort;
import com.ecommerce.port.adapters.gateway.AuthenticationGateway;
import com.ecommerce.port.adapters.repositories.UserRepositoryPort;
import com.ecommerce.util.message.SuccessMessages;

public class UserService implements UserDriverPort {

    private final UserRepositoryPort userRepository;
    private final AuthenticationGateway authenticationGateway;

    public UserService(UserRepositoryPort userRepository, AuthenticationGateway authenticationGateway, AuthenticationGateway authenticationGateway1) {
        this.userRepository = userRepository;
        this.authenticationGateway = authenticationGateway1;
    }

    @Override
    public MessageResponse updateUserInfo(Info info) {
        User user = this.getAuthenticatedUser();
        user.setCivility(info.getCivility());
        user.setFirstName(info.getFirstName());
        user.setLastName(info.getLastName());
        userRepository.save(user);

        return new MessageResponse(SuccessMessages.USER_INFO_SUCCESSFULLY_UPDATED);
    }

    @Override
    public MessageResponse updateUserEmail(Email email) {
        User user = this.getAuthenticatedUser();
        user.setEmail(email.getEmail());
        userRepository.save(user);

        return new MessageResponse(SuccessMessages.USER_EMAIL_SUCCESSFULLY_UPDATED);
    }

    @Override
    public Profile getUserProfile() {

        Info info = User.toInfo(this.getAuthenticatedUser());
        Email email = new Email(this.getAuthenticatedUser().getEmail());

        return new Profile(info, email);
    }

    public User getAuthenticatedUser(){
        return authenticationGateway.getAuthenticatedUser();
    }


}

