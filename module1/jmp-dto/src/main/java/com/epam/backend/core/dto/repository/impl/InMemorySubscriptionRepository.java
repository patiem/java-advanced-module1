package com.epam.backend.core.dto.repository.impl;

import com.epam.backend.core.dto.Subscription;
import com.epam.backend.core.dto.repository.SubscriptionRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InMemorySubscriptionRepository implements SubscriptionRepository {
    private final List<Subscription> subscriptions = new ArrayList<>();

    @Override
    public void addSubscription(Subscription subscription) {
        Optional.ofNullable(subscription)
                        .ifPresent(subscriptions::add);
    }

    @Override
    public void addSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions.addAll(subscriptions);
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return Collections.unmodifiableList(subscriptions);
    }

    @Override
    public List<Subscription> findSubscriptionsByCondition(Predicate<Subscription> condition) {
        return subscriptions.stream()
                .filter(condition)
                .collect(Collectors.toUnmodifiableList());
    }
}
