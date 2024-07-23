package com.rohit.ecommerce.controller;


import com.rohit.ecommerce.customer.Customer;
import com.rohit.ecommerce.customer.CustomerControllerRequest;
import com.rohit.ecommerce.customer.CustomerResponse;
import com.rohit.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {


    private final CustomerService service;

    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerControllerRequest request
    ) {
        return ResponseEntity.ok(this.service.createCustomer(request));
    }


    @PutMapping
    public ResponseEntity<?> updateCustomer(
            @RequestBody @Valid CustomerControllerRequest request
    ) {

        return ResponseEntity.ok( this.service.updateCustomer(request));
    }


    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {

        return ResponseEntity.ok( this.service.findAllCustomers());
    }


    @GetMapping("/exits/{customer-id}")
    public ResponseEntity<Boolean> exitById(@PathVariable("customer-id") String customerId) {

        return ResponseEntity.ok( this.service.exitById(customerId));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findCustomerById(@PathVariable("customer-id") String customerId) {

        return ResponseEntity.ok( this.service.findCustomerById(customerId));
    }


    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customer-id") String customerId) {
        this.service.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }


}
