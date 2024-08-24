package com.example.purchaseandearn.repository;

import com.example.purchaseandearn.entity.Purchases;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PurchasesRepository extends JpaRepository<Purchases, Long> {
    List<Purchases> findByCustomerIdAndDateBetween(Long customerId, LocalDate startDate, LocalDate endDate);
}