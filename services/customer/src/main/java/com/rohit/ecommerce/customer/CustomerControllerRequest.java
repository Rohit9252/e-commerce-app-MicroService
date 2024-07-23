package com.rohit.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerControllerRequest(
         String id,
       @NotNull(message =  "Customer First Name is Required")
       String firstName,
         @NotNull(message =  "Customer Last Name is Required")
         String lastName,
         @NotNull(message =  "Customer Email address is Required")
         @Email(message =  "Email address is not a valid email address")
         String email,

         Address address

) {
}
