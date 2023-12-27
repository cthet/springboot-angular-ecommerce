package com.ecommerce.service;


import com.ecommerce.model.payment.PaymentInfo;
import com.ecommerce.port.drivers.CheckoutDriverPort;
import com.ecommerce.port.repositories.UserRepositoryPort;
import com.ecommerce.security.UserDetailsServiceImpl;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

public class CheckoutService implements CheckoutDriverPort {

    private final UserRepositoryPort userRepository;
    private final UserDetailsServiceImpl userPrincipalService;
    private final String secretKey;

    public CheckoutService(UserRepositoryPort userRepository, UserDetailsServiceImpl userPrincipalService, String secretKey) {
        this.userRepository = userRepository;
        this.userPrincipalService = userPrincipalService;
        this.secretKey = secretKey;
    }

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {

        Stripe.apiKey = secretKey;

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams
                        .builder()
                        .setAmount(paymentInfo.getAmount())
                        .setCurrency(paymentInfo.getCurrency())
                        .addPaymentMethodType("card")
                        .build();

        return PaymentIntent.create(params);
    }

}

