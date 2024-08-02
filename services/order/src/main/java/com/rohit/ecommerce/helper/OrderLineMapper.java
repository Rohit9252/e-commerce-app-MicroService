package com.rohit.ecommerce.helper;


import com.rohit.ecommerce.model.Order;
import com.rohit.ecommerce.orderline.OrderLine;
import com.rohit.ecommerce.orderline.OrderLineRequest;
import com.rohit.ecommerce.response.OrderLineResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {



    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {

        return OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(
                        Order.builder()
                                .id(orderLineRequest.orderId())
                                .build()
                )
                .productId(orderLineRequest.productId())
                .build();

    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {

        return  new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());

    }
}
