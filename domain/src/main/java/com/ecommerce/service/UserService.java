package com.ecommerce.service;

import com.ecommerce.exception.UserPrincipalNotFound;
import com.ecommerce.model.message.MessageResponse;
import com.ecommerce.model.user.Email;
import com.ecommerce.model.user.Info;
import com.ecommerce.model.user.Profile;
import com.ecommerce.model.user.User;
import com.ecommerce.port.drivers.UserDriverPort;
import com.ecommerce.port.repositories.UserRepositoryPort;
import com.ecommerce.security.UserDetailsServiceImpl;
import com.ecommerce.util.message.ErrorMessages;
import com.ecommerce.util.message.SuccessMessages;

public class UserService implements UserDriverPort {

    private final UserRepositoryPort userRepository;
    private final UserDetailsServiceImpl userDetailsService;

    public UserService(UserRepositoryPort userRepository, UserDetailsServiceImpl userDetailsService) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public User getUser() {
        return userRepository.findById(userDetailsService.getUserPrincipalImpl().getId())
                .orElseThrow(() -> new UserPrincipalNotFound(ErrorMessages.USER_PRINCIPAL_NOT_FOUND));
    }


    @Override
    public MessageResponse updateUserInfo(Info info) {

        userRepository.save(info.toUser(info));

        return new MessageResponse(SuccessMessages.USER_INFO_SUCCESSFULLY_UPDATED);
    }

    @Override
    public MessageResponse updateUserEmail(Email email) {

        User user = this.getUser();
        user.setEmail(email.getEmail());

        userRepository.save(user);

        return new MessageResponse(SuccessMessages.USER_EMAIL_SUCCESSFULLY_UPDATED);
    }

    @Override
    public Profile getUserProfile() {

        Info info = User.toInfo(this.getUser());
        Email email = new Email(this.getUser().getEmail());

        return new Profile(info, email);
    }

}

