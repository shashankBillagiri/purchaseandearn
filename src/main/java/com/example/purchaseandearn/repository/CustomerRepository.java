package com.example.purchaseandearn.repository;

import com.example.purchaseandearn.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    int countById(Long id);
}

