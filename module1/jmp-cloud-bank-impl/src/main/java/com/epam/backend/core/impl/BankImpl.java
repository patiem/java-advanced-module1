package com.epam.backend.core.impl;

import com.epam.backend.core.dto.BankCard;
import com.epam.backend.core.dto.BankCardType;
import com.epam.backend.core.dto.User;
import com.epam.backend.core.service.Bank;
import com.epam.backend.core.factory.BankCardFactory;


public class BankImpl implements Bank {
    @Override
    public BankCard createBankCard(User user, BankCardType cardType) {
        return BankCardFactory.createBankCard(user, cardType);
    }
}
