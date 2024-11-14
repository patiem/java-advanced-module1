package com.epam.backend.core.factory;

import com.epam.backend.core.dto.BankCard;
import com.epam.backend.core.dto.BankCardType;
import com.epam.backend.core.dto.CreditBankCard;
import com.epam.backend.core.dto.DebitBankCard;
import com.epam.backend.core.dto.User;

import java.util.Map;
import java.util.function.Function;

public class BankCardFactory {
    private static final Map<BankCardType, Function<User, BankCard>> cardMap = Map.of(
        BankCardType.CREDIT, CreditBankCard::new,
        BankCardType.DEBIT, DebitBankCard::new
    );

    private BankCardFactory(){}

    public static BankCard createBankCard(User user, BankCardType cardType) {

        Function<User, BankCard> cardSupplier = cardMap.get(cardType);
        if (cardSupplier == null) {
            throw new IllegalArgumentException("Unknown card type: " + cardType);
        }
        return cardSupplier.apply(user);
    }
}