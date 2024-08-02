package com.rohit.ecommerce.orderline;

import com.rohit.ecommerce.model.Order;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    private Integer productId;


    private Double quantity;






}
