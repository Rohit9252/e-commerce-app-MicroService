package com.rohit.ecommerce.service;

import com.rohit.ecommerce.customer.CustomerClient;
import com.rohit.ecommerce.exception.BusinessException;
import com.rohit.ecommerce.helper.OrderMapper;
import com.rohit.ecommerce.kafka.OrderConfirmation;
import com.rohit.ecommerce.kafka.OrderProducer;
import com.rohit.ecommerce.orderline.OrderLineRequest;
import com.rohit.ecommerce.orderline.OrderLineService;
import com.rohit.ecommerce.payment.PaymentClient;
import com.rohit.ecommerce.payment.PaymentRequest;
import com.rohit.ecommerce.product.ProductClient;
import com.rohit.ecommerce.repository.OrderRepository;
import com.rohit.ecommerce.request.OrderRequest;
import com.rohit.ecommerce.request.PurchaseRequest;
import com.rohit.ecommerce.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {



    private  final CustomerClient customerClient;
    private  final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private  final  OrderLineService orderLineService;
    private  final OrderProducer orderProducer;
    private final PaymentClient paymentClient;




    public Integer createOrder(OrderRequest orderRequest) {
        // check the customer  openfeign --> customer microservice
        var customer = customerClient.findCustomerById(orderRequest.customerId())
                        .orElseThrow(() -> new BusinessException("Cannot Create Order:: Customer Not Found"));
        // purchase the products --> using product microservice (RestTemplate)

        var purchasedProducts = productClient.purchaseProducts(orderRequest.products());

        //persist order
        var order = orderRepository.save(
            mapper.toOrder(orderRequest)
        );

        // persist order line

        for (PurchaseRequest  purchaseRequest: orderRequest.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );

        }

        // start payment process
        var paymentRequest = new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        // send the order confirmation  --> notification-ms (Kafka)

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchasedProducts

                )
        );


        return order.getId();
    }


    public List<OrderResponse> findAll() {

            return  orderRepository.findAll()
                    .stream()
                    .map(mapper::fromOrder)
                    .collect(Collectors.toList());




    }

    public OrderResponse findById(Integer orderId) {

        return orderRepository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new BusinessException(String.format("No Order Found with Id %s", orderId)));


    }
}
