package com.epam.backend.core.impl;

import com.epam.backend.core.dto.BankCard;
import com.epam.backend.core.dto.BankCardType;
import com.epam.backend.core.dto.CreditBankCard;
import com.epam.backend.core.dto.DebitBankCard;
import com.epam.backend.core.dto.User;
import com.epam.backend.core.service.Bank;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.SoftAssertions;
import java.time.LocalDate;


class BankImplTest {

    private Bank bank = new BankImpl();

    @Test
    public void testCreateCreditBankCard() {
        var user = new User("John", "Doe", LocalDate.of(1990, 1, 1));

        BankCard bankCard = bank.createBankCard(user, BankCardType.CREDIT);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(bankCard).isInstanceOf(CreditBankCard.class);
        softly.assertThat(bankCard.getUser().getName()).isEqualTo("John");
        softly.assertThat(bankCard.getUser().getSurname()).isEqualTo("Doe");
        softly.assertThat(bankCard.getUser().getBirthday()).isEqualTo(LocalDate.of(1990, 1, 1));
        softly.assertAll();
    }

    @Test
    public void testCreateDebitBankCard() {
        var user = new User("Kate", "Small", LocalDate.of(1992, 2, 12));
        var bankCard = bank.createBankCard(user, BankCardType.DEBIT);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(bankCard).isInstanceOf(DebitBankCard.class);
        softly.assertThat(bankCard.getUser().getName()).isEqualTo("Kate");
        softly.assertThat(bankCard.getUser().getSurname()).isEqualTo("Small");
        softly.assertThat(bankCard.getUser().getBirthday()).isEqualTo(LocalDate.of(1992, 2, 12));
        softly.assertAll();
    }

    @Test
    public void testCreateBankCardWithUnknownType() {
        var user = new User("Alice", "Smith", LocalDate.of(1995, 7, 3));

        SoftAssertions softly = new SoftAssertions();
        softly.assertThatThrownBy(() -> bank.createBankCard(user, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unknown card type");
        softly.assertAll();
    }
}