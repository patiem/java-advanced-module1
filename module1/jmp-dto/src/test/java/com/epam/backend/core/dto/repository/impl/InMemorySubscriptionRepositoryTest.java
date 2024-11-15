package com.epam.backend.core.dto.repository.impl;

import com.epam.backend.core.dto.Subscription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class InMemorySubscriptionRepositoryTest {

    public static final String BANK_CARD_NUMBER_1 = "1234567890123456";
    public static final Subscription SUBSCRIPTION1 = new Subscription(BANK_CARD_NUMBER_1, LocalDate.of(2021, 1, 1));
    public static final Subscription SUBSCRIPTION2 = new Subscription("6543210987654321", LocalDate.of(2022, 2, 2));
    private InMemorySubscriptionRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new InMemorySubscriptionRepository();
        repository.addSubscription(SUBSCRIPTION1);
        repository.addSubscription(SUBSCRIPTION2);
    }

    @Test
    public void testAddSubscription() {
        var newSubscription = new Subscription("1111222233334444", LocalDate.of(2023, 3, 3));
        repository.addSubscription(newSubscription);
        var allSubscriptions = repository.getAllSubscriptions();
        assertTrue(allSubscriptions.contains(newSubscription));
    }

    @Test
    public void testAddSubscriptions() {
        var newSubscriptions = List.of(
                new Subscription("5555666677778888", LocalDate.of(2024, 4, 4)),
                new Subscription("9999000011112222", LocalDate.of(2025, 5, 5))
        );
        repository.addSubscriptions(newSubscriptions);
        var allSubscriptions = repository.getAllSubscriptions();
        assertTrue(allSubscriptions.containsAll(newSubscriptions));
    }

    @Test
    public void testGetAllSubscriptions() {
       var allSubscriptions = repository.getAllSubscriptions();
        assertEquals(2, allSubscriptions.size());
    }

    @Test
    public void testFindSubscriptionsByCondition() {
        var foundSubscriptions = repository.findSubscriptionsByCondition(
                subscription -> BANK_CARD_NUMBER_1.equals(subscription.getBankCardNumber())
        );
        assertEquals(1, foundSubscriptions.size());
        assertEquals(BANK_CARD_NUMBER_1, foundSubscriptions.get(0).getBankCardNumber());
    }
}