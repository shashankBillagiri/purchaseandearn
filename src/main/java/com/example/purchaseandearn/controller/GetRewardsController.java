package com.example.purchaseandearn.controller;

import com.example.purchaseandearn.exception.CustomerNotFoundException;
import com.example.purchaseandearn.service.CustomerService;
import com.example.purchaseandearn.service.RewardsCaluclationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

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

    @GetMapping("/{customerId}")
    public CompletableFuture<Map<String, Integer>> getMonthlyAndTotalPoints(@PathVariable Long customerId) throws CustomerNotFoundException {
        logger.info("event=getMonthlyAndTotalPoints, received request to fetch monthlyAndTotal points for customer with id = {}", customerId);

        if (customerService.isValidCustomerID(customerId)) {
            return rewardService.getMonthlyAndTotalPoints(customerId);
        }

        throw new CustomerNotFoundException(String.format(CUSTOMER_DOES_NOT_EXIST));

    }

}
