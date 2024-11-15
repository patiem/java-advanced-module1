package com.epam.backend.core;

import com.epam.backend.core.dto.BankCardType;
import com.epam.backend.core.dto.User;
import com.epam.backend.core.dto.repository.impl.InMemorySubscriptionRepository;
import com.epam.backend.core.dto.repository.impl.InMemoryUserRepository;
import com.epam.backend.core.impl.BankImpl;
import com.epam.backend.core.impl.ServiceImpl;

import java.time.LocalDate;

public class BankingApplication {
    public static void main(String[] args) {
        // Initialize repositories
        var userRepository = new InMemoryUserRepository();
        var subscriptionRepository = new InMemorySubscriptionRepository();

        // Initialize services
        var service = new ServiceImpl(subscriptionRepository, userRepository);
        var bank = new BankImpl();

        // Create and add users
        var user1 = new User("Mike", "Seven", LocalDate.of(2000, 11, 1));
        var user2 = new User("Elle", "Nine", LocalDate.of(1992, 2, 16));
        userRepository.addUser(user1);
        userRepository.addUser(user2);

        // Create bank cards for users
        var creditCardJohn = bank.createBankCard(user1, BankCardType.CREDIT);
        var debitCardJane = bank.createBankCard(user2, BankCardType.DEBIT);

        // Subscribe users to services using their bank cards
        service.subscribe(creditCardJohn);
        service.subscribe(debitCardJane);

        // Display all users
        System.out.println("All Users:");
        var users = userRepository.getAllUsers();
        users.forEach(user -> System.out.println(user.getName() + " " + user.getSurname()));

        // Display all subscriptions
        System.out.println("\nAll Subscriptions:");
        var subscriptions = subscriptionRepository.getAllSubscriptions();
        subscriptions.forEach(subscription -> System.out.println("Card Number: " + subscription.getBankCardNumber() + ", Start Date: " + subscription.getStartDate()));

        // Example of finding a specific subscription
        System.out.println("\nSubscription Details for Card Number: " + creditCardJohn.getNumber());
        var specificSubscriptions = subscriptionRepository.findSubscriptionsByCondition(
                subscription -> subscription.getBankCardNumber().equals(creditCardJohn.getNumber())
        );
        specificSubscriptions.forEach(subscription -> System.out.println("Card Number: " + subscription.getBankCardNumber() + ", Start Date: " + subscription.getStartDate()));
    }
}