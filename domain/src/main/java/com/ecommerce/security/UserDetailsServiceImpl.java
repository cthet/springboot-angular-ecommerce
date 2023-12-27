package com.ecommerce.security;

import com.ecommerce.exception.UserNotConnected;
import com.ecommerce.exception.UserNotFoundByEmail;
import com.ecommerce.model.user.User;
import com.ecommerce.port.repositories.UserRepositoryPort;
import com.ecommerce.util.message.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepositoryPort userRepository;

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundByEmail(ErrorMessages.USER_NOT_FOUND_WITH_EMAIL + email, email));

        return UserDetailsImpl.build(user);
    }

    public UserDetailsImpl getUserPrincipalImpl() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if(principal instanceof UserDetailsImpl){
            return (UserDetailsImpl) principal;
        } else {
            throw new UserNotConnected(ErrorMessages.USER_NOT_CONNECTED);
        }
    }

}