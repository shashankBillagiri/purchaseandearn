package com.example.purchaseandearn.service;

import com.example.purchaseandearn.entity.Purchases;
import com.example.purchaseandearn.repository.PurchasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class RewardsCaluclationService {

    @Autowired
    private PurchasesRepository purchasesRepository;

    public int calculateRewardPoints(double amount) {
        int rewardPoints = 0;

        if (amount > 100) {
            rewardPoints += 2 * (amount - 100);

            rewardPoints += 1 * 50;
        } else if (amount > 50) {
            rewardPoints += (amount - 50);
        }
        return rewardPoints;
    }

    public CompletableFuture<Map<String, Integer>> getMonthlyAndTotalPoints(Long customerId) {
        LocalDate now = LocalDate.now();
        LocalDate threeMonthsAgo = now.minusMonths(3);
        List<Purchases> totalPurchases = purchasesRepository.findByCustomerIdAndDateBetween(customerId, threeMonthsAgo, now);

        // To Calculate monthly rewards
        Map<String, Integer> monthlyPoints = totalPurchases.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getDate().getMonth().toString(),
                        Collectors.summingInt(t -> calculateRewardPoints(t.getAmount()))
                ));

        // To Calculate total rewards for last three months
        int totalPoints = totalPurchases.stream()
                .mapToInt(t -> calculateRewardPoints(t.getAmount()))
                .sum();

        // Create a LinkedHashMap so that the result has total rewards shown at the end
        Map<String, Integer> result = new LinkedHashMap<>(monthlyPoints);
        result.put("TotalRewardsEarnedinLastThreeMonths", totalPoints);

        return CompletableFuture.completedFuture(result);
    }

}
