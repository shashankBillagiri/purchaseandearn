package com.example.purchaseandearn.controller;

import com.example.purchaseandearn.service.RewardsCaluclationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.example.purchaseandearn.util.RewardsUtility.REQUEST_MAPPING;

@RestController
@RequestMapping(REQUEST_MAPPING)
public class GetRewardsController {

    @Autowired
    private RewardsCaluclationService rewardService;

    @GetMapping("/{customerId}")
    public CompletableFuture<Map<String, Integer>> getMonthlyAndTotalPoints(@PathVariable Long customerId) {
        return rewardService.getMonthlyAndTotalPoints(customerId);
    }
}
