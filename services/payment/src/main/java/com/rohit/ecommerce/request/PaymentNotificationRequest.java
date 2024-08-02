package com.rohit.ecommerce.request;


import com.rohit.ecommerce.customer.Customer;
import com.rohit.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(

    String orderReference,
    BigDecimal amount,
    PaymentMethod paymentMethod,

    String customerFirstName,
    String customerLastName,
    String customerEmail



) {
}
