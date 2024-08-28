package com.example.purchaseandearn.controller;

import com.example.purchaseandearn.exception.CustomerNotFoundException;
import com.example.purchaseandearn.service.CustomerService;
import com.example.purchaseandearn.service.RewardsCaluclationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.example.purchaseandearn.util.RewardsUtility.CUSTOMER_DOES_NOT_EXIST;
import static com.example.purchaseandearn.util.RewardsUtility.REQUEST_MAPPING;

@Validated
@RestController
@RequestMapping(REQUEST_MAPPING)
public class GetRewardsController {

    private static final Logger logger = LogManager.getLogger(GetRewardsController.class);

    @Autowired
    private RewardsCaluclationService rewardService;

    @Autowired
    private CustomerService customerService;

    @GetMapping({"/customerIds"})
    public CompletableFuture<Map<Long, Map<String, Integer>>> getMonthlyAndTotalPointsForCustomers(@RequestParam List<Long> customerIds) throws CustomerNotFoundException {
        logger.info("event=getMonthlyAndTotalPointsForCustomers, received request to fetch monthlyAndTotal points for customers with ids = {}", customerIds);
        Map<Long, CompletableFuture<Map<String, Integer>>> customerRewardPoints = new HashMap<>();

        for (Long customerId : customerIds) {
            if (this.customerService.isValidCustomerID(customerId)) {
                customerRewardPoints.put(customerId, this.rewardService.getMonthlyAndTotalPoints(customerId));
            } else {
                logger.info("event=getMonthlyAndTotalPointsForCustomers, requested customer with id {} does not exist", customerId);
                throw new CustomerNotFoundException(String.format("There are no Customers existing with the requested CustomerID: %s Please check the customerID's and provide Valid CustomerID", customerId));
            }
        }

        return CompletableFuture.allOf(customerRewardPoints.values().toArray(new CompletableFuture[0]))
                .thenApply(v -> customerRewardPoints.entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().join())));
    }


}
