package com.ecommerce.security;

import com.ecommerce.exception.UserNotConnected;
import com.ecommerce.model.user.User;
import com.ecommerce.port.adapters.repositories.UserRepositoryPort;
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
        User user = userRepository.findByEmail(email);

        return UserDetailsImpl.build(user);
    }

}