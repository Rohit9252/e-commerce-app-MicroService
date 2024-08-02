package com.rohit.ecommerce.orderline;


import com.rohit.ecommerce.helper.OrderLineMapper;
import com.rohit.ecommerce.repository.OrderLineRepository;
import com.rohit.ecommerce.response.OrderLineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private  final OrderLineRepository orderLineRepository;

    private  final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {

        var orderLine = orderLineMapper.toOrderLine(orderLineRequest);

        return orderLineRepository.save(orderLine).getId();


    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {


        return  orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::toOrderLineResponse)
                .toList();

    }
}
