package com.epam.backend.core.dto.repository.impl;

import com.epam.backend.core.dto.Subscription;
import com.epam.backend.core.dto.repository.SubscriptionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InMemorySubscriptionRepository implements SubscriptionRepository {
    private final List<Subscription> subscriptions = new ArrayList<>();

    @Override
    public void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }

    @Override
    public void addSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions.addAll(subscriptions);
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptions
                .stream()
                .collect(Collectors.toUnmodifiableList()); // Return a copy to prevent external modifications
    }

    @Override
    public List<Subscription> findSubscriptionsByCondition(Predicate<Subscription> condition) {
        return subscriptions.stream()
                .filter(condition)
                .collect(Collectors.toUnmodifiableList());
    }
}
