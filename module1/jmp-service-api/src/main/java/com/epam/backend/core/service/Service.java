package com.epam.backend.core.service;

import com.epam.backend.core.dto.BankCard;
import com.epam.backend.core.dto.Subscription;
import com.epam.backend.core.dto.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {

    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition);

    List<User> getAllUsers();

    default double getAverageUsersAge(){
        return getAllUsers().stream()
                .filter(user -> Objects.nonNull(user.getBirthday()))
                .mapToDouble(user -> ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()))
                    .average()
                    .orElse(0.0);
    }

    static boolean isPayableUser(User user){
        return ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()) >= 18;
    }
}
