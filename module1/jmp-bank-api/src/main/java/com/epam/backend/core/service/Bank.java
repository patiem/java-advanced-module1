package com.epam.backend.core.service;

import com.epam.backend.core.dto.BankCard;
import com.epam.backend.core.dto.BankCardType;
import com.epam.backend.core.dto.User;

public interface Bank {
    BankCard createBankCard(User user, BankCardType cardType);
}
