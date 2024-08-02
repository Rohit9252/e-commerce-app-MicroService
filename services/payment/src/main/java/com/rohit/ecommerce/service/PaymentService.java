package com.rohit.ecommerce.service;


import com.rohit.ecommerce.helper.PaymentMapper;
import com.rohit.ecommerce.notification.NotificationProducer;
import com.rohit.ecommerce.repository.PaymentRepository;
import com.rohit.ecommerce.request.PaymentNotificationRequest;
import com.rohit.ecommerce.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {


    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;



    public Integer createPayment(PaymentRequest paymentRequest) {

        var payment = repository.save(mapper.toPayment(paymentRequest));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        paymentRequest.orderReference(),
                        paymentRequest.amount(),
                        paymentRequest.paymentMethod(),
                        paymentRequest.customer().firstName(),
                        paymentRequest.customer().lastName(),
                        paymentRequest.customer().email()
                )
        );


        return payment.getId();
    }




}
