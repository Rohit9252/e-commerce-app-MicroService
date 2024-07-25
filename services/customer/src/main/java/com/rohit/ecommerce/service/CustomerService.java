package com.rohit.ecommerce.service;



import com.rohit.ecommerce.customer.Customer;
import com.rohit.ecommerce.customer.CustomerControllerRequest;
import com.rohit.ecommerce.customer.CustomerMapper;
import com.rohit.ecommerce.customer.CustomerResponse;
import com.rohit.ecommerce.exception.CustomerNotFoundException;
import com.rohit.ecommerce.respository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private  final CustomerRepository customerRepository;
    private  final CustomerMapper customerMapper;


    public String createCustomer(CustomerControllerRequest request) {

        var customer = customerRepository.save(customerMapper.toCustomer(request));


        return customer.getId();
    }

    public String updateCustomer(CustomerControllerRequest request) {

        var customer = customerRepository.findById(request.id())
                .orElseThrow( ()-> new CustomerNotFoundException(
                        format("Cannot Update Customer:: No Customer found with the provided ID:: %s", request.id())
                ));

        mergeCustomer(customer, request);

        customerRepository.save(customer);

        return "Customer is Upadated";


    }

    private void mergeCustomer(Customer customer, CustomerControllerRequest request) {

        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }

        if(StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address() != null){
            customer.setAddress(request.address());
        }



    }

    public List<CustomerResponse> findAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customerMapper::FromCustomer)
                .collect(Collectors.toList());
    }

    public CustomerResponse findCustomerById(String id) {


        var customer =  customerRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException(
                        format("Cannot Find Customer:: No Customer found with the provided ID:: %s", id)
                ));


        var customerResponse = customerMapper.FromCustomer(customer);

        return customerResponse;

    }

    public Boolean exitById(String customerId) {

        return customerRepository.findById(customerId).isPresent();
    }

    public void deleteCustomer(String customerId) {

        var customer =  customerRepository.findById(customerId)
                .orElseThrow(()-> new CustomerNotFoundException(
                        format("Cannot Find Customer:: No Customer found with the provided ID:: %s", customerId)
                ));
        customerRepository.deleteById(customerId);
    }
}
