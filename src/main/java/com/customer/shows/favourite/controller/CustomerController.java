package com.customer.shows.favourite.controller;

import com.customer.shows.favourite.domain.Customer;
import com.customer.shows.favourite.dto.CustomerDTO;
import com.customer.shows.favourite.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService userService) {
        this.customerService = userService;
    }

    @PostMapping
    public void createCustomer(@RequestBody CustomerDTO customer) {
        customerService.createCustomer(customer);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> getCustomerDetails(@PathVariable("id") Long id) {

        CustomerDTO customerDetails = customerService.getUserDetails(id);
        return ResponseEntity.ok(customerDetails);
    }

}
