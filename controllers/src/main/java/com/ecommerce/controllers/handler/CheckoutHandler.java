package com.ecommerce.controllers.handler;

import com.ecommerce.adapters.repository.springdata.adapters.UserRepositoryJpaAdapter;
import com.ecommerce.controllers.stripe.CheckoutService;
import com.ecommerce.controllers.stripe.StripeService;
import org.springframework.stereotype.Service;

@Service
public class CheckoutHandler extends CheckoutService {


    public CheckoutHandler(UserRepositoryJpaAdapter userRepository, StripeService stripeService) {
        super(userRepository, stripeService);
    }

}
