package com.customer.shows.favourite.service;

import com.customer.shows.favourite.dao.CustomerRepository;
import com.customer.shows.favourite.domain.Customer;
import com.customer.shows.favourite.dto.CustomerDTO;
import com.customer.shows.favourite.exceptions.CustomerNotFoundException;
import com.customer.shows.favourite.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository userRepository;

    private CustomerMapper customerMapper;


    @Autowired
    public CustomerService(CustomerRepository userRepository, CustomerMapper customerMapper) {
        this.userRepository = userRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerDTO getCustomerDetails(Long id) {

        Optional<Customer> customer = userRepository.findById(id);
        if(customer.isPresent()) {
            return customerMapper.mapToDTO(customer.get());
        } else {
            throw new CustomerNotFoundException(String.valueOf(id));
        }
    }

    public Long createCustomer(CustomerDTO customerDTO) {
        Customer customer = this.userRepository.save(customerMapper.mapToEntity(customerDTO));
        return customer.getId();
    }

    public List<CustomerDTO> findAllCustomers() {
        List<Customer> customerList = this.userRepository.findAll();
        return customerList.stream().map(customerMapper::mapToDTO).collect(Collectors.toList());
    }
}
