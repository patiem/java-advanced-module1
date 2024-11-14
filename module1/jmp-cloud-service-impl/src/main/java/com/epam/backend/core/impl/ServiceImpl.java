package com.epam.backend.core.impl;

import com.epam.backend.core.dto.BankCard;
import com.epam.backend.core.dto.Subscription;
import com.epam.backend.core.dto.User;
import com.epam.backend.core.service.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


public class ServiceImpl implements Service {
    @Override
    public void subscribe(BankCard bankCard) {

    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        return Optional.empty();
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return Collections.EMPTY_LIST;
    }
}
