package com.customer.shows.favourite.controller;

import com.customer.shows.favourite.dto.CustomerDTO;
import com.customer.shows.favourite.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService userService) {
        this.customerService = userService;
    }

    @PostMapping("/customer")
    public ResponseEntity<Long> createCustomer(@RequestBody CustomerDTO customer) {
        Long customerId = this.customerService.createCustomer(customer);
        return ResponseEntity.ok(customerId);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> getCustomerDetails(@PathVariable("id") Long id) {
        CustomerDTO customerDetails = customerService.getCustomerDetails(id);
        return ResponseEntity.ok(customerDetails);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> allCustomers = customerService.findAllCustomers();
        return ResponseEntity.ok(allCustomers);
    }

}