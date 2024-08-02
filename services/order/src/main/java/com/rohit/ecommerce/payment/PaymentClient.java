package com.rohit.ecommerce.payment;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url= "{application.config.payment-url}" // This is the URL of the payment service
)
public interface PaymentClient {



    @PostMapping
    Integer requestOrderPayment(
            @RequestBody PaymentRequest paymentRequest
    );

}
