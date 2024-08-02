package com.rohit.ecommerce.response;


import com.rohit.ecommerce.model.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(

        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId

) {
}
