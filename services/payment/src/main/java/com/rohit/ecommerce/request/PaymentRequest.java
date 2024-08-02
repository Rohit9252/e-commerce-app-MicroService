package com.rohit.ecommerce.request;

import com.rohit.ecommerce.customer.Customer;
import com.rohit.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(

        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,

        Integer orderId,
        String orderReference,
        Customer customer



) {
}
