package com.rohit.ecommerce.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(


        @NotNull(message = "Product is Mandatory")
        Integer productId,


        @Positive(message = "Quantity should be positive")
        Double quantity


) {



}
