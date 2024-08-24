package com.example.purchaseandearn.service;

import com.example.purchaseandearn.entity.Purchases;
import com.example.purchaseandearn.repository.PurchasesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RewardsCalculationServiceTest {

    @Mock
    private PurchasesRepository purchasesRepository;

    @InjectMocks
    private RewardsCaluclationService rewardsCalculationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateRewardPoints() {
        assertEquals(0, rewardsCalculationService.calculateRewardPoints(50));
        assertEquals(50, rewardsCalculationService.calculateRewardPoints(100));
        assertEquals(150, rewardsCalculationService.calculateRewardPoints(150));
        assertEquals(250, rewardsCalculationService.calculateRewardPoints(200));
    }

    @Test
    void testGetMonthlyAndTotalPoints() {

        LocalDate now = LocalDate.now();
        LocalDate threeMonthsAgo = now.minusMonths(3);

        Purchases purchase1 = new Purchases();
        purchase1.setDate(LocalDate.of(2024, 6, 15));
        purchase1.setAmount(120.0);

        Purchases purchase2 = new Purchases();
        purchase2.setDate(LocalDate.of(2024, 7, 10));
        purchase2.setAmount(80.0);

        Purchases purchase3 = new Purchases();
        purchase3.setDate(LocalDate.of(2024, 8, 5));
        purchase3.setAmount(50.0);

        Purchases purchase4 = new Purchases();
        purchase4.setDate(LocalDate.of(2024, 8, 20));
        purchase4.setAmount(200.0);

        List<Purchases> purchases = Arrays.asList(purchase1, purchase2, purchase3, purchase4);

        when(purchasesRepository.findByCustomerIdAndDateBetween(1L, threeMonthsAgo, now))
                .thenReturn(purchases);

        Map<String, Integer> expectedResult = new LinkedHashMap<>();
        expectedResult.put("JUNE", 90);
        expectedResult.put("AUGUST", 250);
        expectedResult.put("JULY", 30);
        expectedResult.put("TotalRewardsEarnedinLastThreeMonths", 370);

        CompletableFuture<Map<String, Integer>> futureResult = rewardsCalculationService.getMonthlyAndTotalPoints(1L);
        Map<String, Integer> result = futureResult.join();

        assertEquals(expectedResult, result);
    }
}
