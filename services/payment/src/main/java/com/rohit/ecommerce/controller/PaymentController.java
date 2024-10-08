package com.rohit.ecommerce.controller;


import com.rohit.ecommerce.request.PaymentRequest;
import com.rohit.ecommerce.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {


    private final PaymentService paymentService;


    @PostMapping
    public ResponseEntity<Integer> createPaymnent(
            @RequestBody @Valid PaymentRequest paymentRequest
    ){
        return ResponseEntity.ok(paymentService.createPayment(paymentRequest));


    }



}
