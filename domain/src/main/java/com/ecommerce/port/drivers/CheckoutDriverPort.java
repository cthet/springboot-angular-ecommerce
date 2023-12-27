package com.ecommerce.port.drivers;

import com.ecommerce.model.payment.PaymentInfo;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutDriverPort {

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
