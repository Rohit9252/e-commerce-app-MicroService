package com.rohit.ecommerce.helper;


import com.rohit.ecommerce.model.Order;
import com.rohit.ecommerce.request.OrderRequest;
import com.rohit.ecommerce.response.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {




    public Order toOrder(OrderRequest orderRequest){

        return Order.builder()
                .id(orderRequest.id())
                .customerId(orderRequest.customerId())
                .reference(orderRequest.reference())
                .totalAmount(orderRequest.amount())
                .paymentMethod(orderRequest.paymentMethod())
                .build();

    }

    public OrderResponse fromOrder(Order order) {

            return new OrderResponse(
                    order.getId(),
                    order.getReference(),
                    order.getTotalAmount(),
                    order.getPaymentMethod(),
                    order.getCustomerId()
            );

    }
}
