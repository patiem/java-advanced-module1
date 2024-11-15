package com.epam.backend.core.factory;

import com.epam.backend.core.dto.BankCard;
import com.epam.backend.core.dto.BankCardType;
import com.epam.backend.core.dto.CreditBankCard;
import com.epam.backend.core.dto.DebitBankCard;
import com.epam.backend.core.dto.User;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.SoftAssertions;
import java.time.LocalDate;

class BankCardFactoryTest {
    private static final User TEST_USER = new User("John", "Dot", LocalDate.of(2000, 11, 11));

    @Test
    public void testCreateCreditBankCard() {
        BankCard bankCard = BankCardFactory.createBankCard(TEST_USER, BankCardType.CREDIT);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(bankCard).isInstanceOf(CreditBankCard.class);
        softly.assertThat(bankCard.getUser()).isEqualTo(TEST_USER);
        softly.assertAll();
    }

    @Test
    public void testCreateDebitBankCard() {
        BankCard bankCard = BankCardFactory.createBankCard(TEST_USER, BankCardType.DEBIT);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(bankCard).isInstanceOf(DebitBankCard.class);
        softly.assertThat(bankCard.getUser()).isEqualTo(TEST_USER);
        softly.assertAll();
    }

    @Test
    public void testCreateBankCardWithUnknownType() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThatThrownBy(() -> BankCardFactory.createBankCard(TEST_USER, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unknown card type: null");
        softly.assertAll();
    }
}