package com.example.purchaseandearn.service;

import com.example.purchaseandearn.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public boolean isValidCustomerID(Long customerId) {
        return customerRepository.countById(customerId) == 1;
    }
}
