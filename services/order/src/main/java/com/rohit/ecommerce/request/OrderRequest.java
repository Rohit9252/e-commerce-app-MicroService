package com.rohit.ecommerce.request;

import com.rohit.ecommerce.model.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(

        Integer id,
        String reference,

        @Positive(message = "Order amount must be positive")
        BigDecimal amount,

        @NotNull(message = "Payment method Should be precised")
        PaymentMethod paymentMethod,

        @NotNull(message = "Customer Should be Present")
        @NotEmpty(message = "Customer Should be Present")
        @NotBlank(message = "Customer Should be Present")
        String customerId,

        @NotEmpty(message = "You Should have at least one product")
        List<PurchaseRequest> products


) {




}
