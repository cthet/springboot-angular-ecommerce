package com.ecommerce.service;


import com.ecommerce.model.payment.PaymentInfo;
import com.ecommerce.port.drivers.CheckoutDriverPort;
import com.ecommerce.port.adapters.repositories.UserRepositoryPort;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

public class CheckoutService implements CheckoutDriverPort {

    private final UserRepositoryPort userRepository;
    private final String secretKey;

    public CheckoutService(UserRepositoryPort userRepository, String secretKey) {
        this.userRepository = userRepository;
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

