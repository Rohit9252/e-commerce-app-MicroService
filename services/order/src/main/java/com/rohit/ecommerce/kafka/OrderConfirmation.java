package com.rohit.ecommerce.kafka;

import com.rohit.ecommerce.customer.CustomerResponse;
import com.rohit.ecommerce.model.PaymentMethod;
import com.rohit.ecommerce.response.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(

        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
