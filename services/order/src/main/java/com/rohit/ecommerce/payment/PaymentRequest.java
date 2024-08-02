package com.rohit.ecommerce.payment;

import com.rohit.ecommerce.customer.CustomerResponse;
import com.rohit.ecommerce.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(


        BigDecimal amount,
        PaymentMethod paymentMethod,

        Integer orderId,
        String orderReference,
        CustomerResponse customer



) {
}
