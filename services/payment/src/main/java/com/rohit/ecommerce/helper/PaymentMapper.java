package com.rohit.ecommerce.helper;

import com.rohit.ecommerce.payment.Payment;
import com.rohit.ecommerce.request.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {





    public Payment toPayment(PaymentRequest paymentRequest) {

        return Payment.builder()
                .id(paymentRequest.id())
                .orderId(paymentRequest.orderId())
                .paymentMethod(paymentRequest.paymentMethod())
                .amount(paymentRequest.amount())
                .build();

    }
}
