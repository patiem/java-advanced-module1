package com.epam.backend.core.impl;

import com.epam.backend.core.dto.BankCard;
import com.epam.backend.core.dto.Subscription;
import com.epam.backend.core.dto.User;
import com.epam.backend.core.dto.repository.SubscriptionRepository;
import com.epam.backend.core.dto.repository.UserRepository;
import com.epam.backend.core.dto.repository.impl.SubscriptionNotFoundException;
import com.epam.backend.core.service.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ServiceImpl implements Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceImpl.class);
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public ServiceImpl(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void subscribe(BankCard bankCard) {
        var cardNumber = bankCard.getNumber();
        getAllSubscriptionsByCondition(subscription -> cardNumber.equals(subscription.getBankCardNumber()))
                .stream().findFirst()
                .ifPresentOrElse(subscription -> LOGGER.info("Subscription  for card {} already exists.", cardNumber),
                        () -> subscriptionRepository.addSubscription(Subscription.of(cardNumber, LocalDate.now())));
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        return Optional.ofNullable(subscriptionRepository.findSubscriptionsByCondition(subscription -> cardNumber.equals(subscription.getBankCardNumber()))
                .stream().findFirst()
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found for bank card number: " + cardNumber)));
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition) {
        return subscriptionRepository.findSubscriptionsByCondition(condition);

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
