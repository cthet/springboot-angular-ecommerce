package com.ecommerce.controllers.stripe;


import com.ecommerce.domain.port.adapters.repositories.UserRepositoryPort;
import com.ecommerce.domain.model.payment.PaymentInfo;
import com.ecommerce.domain.port.drivers.CheckoutDriverPort;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

public class CheckoutService implements CheckoutDriverPort {

    protected UserRepositoryPort userRepository;
    private StripeService stripeService;

    public CheckoutService(UserRepositoryPort userRepository, StripeService stripeService) {
        this.userRepository = userRepository;
        this.stripeService = stripeService;
    }

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams
                        .builder()
                        .setAmount(paymentInfo.getAmount())
                        .setCurrency(paymentInfo.getCurrency())
                        .addPaymentMethodType("card")
                        .build();

        return stripeService.createPaymentIntent(params);
    }



}

