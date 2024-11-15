package com.epam.backend.core.impl;

import com.epam.backend.core.dto.BankCard;
import com.epam.backend.core.dto.Subscription;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.epam.backend.core.dto.User;
import com.epam.backend.core.dto.repository.SubscriptionRepository;
import com.epam.backend.core.dto.repository.UserRepository;
import com.epam.backend.core.dto.repository.impl.SubscriptionNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.assertj.core.api.SoftAssertions;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;


class ServiceImplTest {

    private static final String CARD_NUMBER = "1234567890";
    @Mock
    private SubscriptionRepository subscriptionRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BankCard bankCard;

    private ServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new ServiceImpl(subscriptionRepository, userRepository);
    }

    @Test
    public void testSubscribe_NewSubscription() {
        when(bankCard.getNumber()).thenReturn(CARD_NUMBER);
        when(subscriptionRepository.findSubscriptionsByCondition(any())).thenReturn(Collections.emptyList());

        service.subscribe(bankCard);

        verify(subscriptionRepository).addSubscription(any(Subscription.class));
    }

    @Test
    public void testSubscribe_ExistingSubscription() {
        var existingSubscription = new Subscription(CARD_NUMBER, LocalDate.now());
        when(bankCard.getNumber()).thenReturn(CARD_NUMBER);
        when(subscriptionRepository.findSubscriptionsByCondition(any())).thenReturn(Collections.singletonList(existingSubscription));

        service.subscribe(bankCard);

        verify(subscriptionRepository, never()).addSubscription(any(Subscription.class));
    }

    @Test
    public void testGetSubscriptionByBankCardNumber_Found() {
        var subscription = new Subscription(CARD_NUMBER, LocalDate.now());
        when(subscriptionRepository.findSubscriptionsByCondition(any())).thenReturn(Collections.singletonList(subscription));

        var result = service.getSubscriptionByBankCardNumber(CARD_NUMBER);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(result).isPresent();
        softly.assertThat(result.get().getBankCardNumber()).isEqualTo(CARD_NUMBER);
        softly.assertAll();
    }

    @Test
    public void testGetSubscriptionByBankCardNumber_NotFound() {
        when(subscriptionRepository.findSubscriptionsByCondition(any())).thenReturn(Collections.emptyList());

        var exception = assertThrows(SubscriptionNotFoundException.class, () -> {
            service.getSubscriptionByBankCardNumber(CARD_NUMBER);
        });

        assertEquals("Subscription not found for bank card number: " + CARD_NUMBER, exception.getMessage());
    }

    @Test
    public void testGetAllSubscriptionsByCondition() {
        var subscription = new Subscription("1234567890", LocalDate.now());
        when(subscriptionRepository.findSubscriptionsByCondition(any())).thenReturn(Arrays.asList(subscription));

        var result = service.getAllSubscriptionsByCondition(s -> true);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(result).isNotEmpty();
        softly.assertThat(result.size()).isEqualTo(1);
        softly.assertAll();
    }

    @Test
    public void testGetAllUsers() {
        var user = new User("John", "Dot", LocalDate.of(2000,10,12));

        when(userRepository.getAllUsers()).thenReturn(Collections.singletonList(user));

        var result = service.getAllUsers();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(result).isNotEmpty();
        softly.assertThat(result.size()).isEqualTo(1);
        softly.assertThat(result.get(0).getName()).isEqualTo("John");
        softly.assertAll();
    }
}