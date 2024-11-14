package com.epam.backend.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CreditBankCard extends BankCard {
    private double creditLimit;

    public CreditBankCard(User user) {
    super(user);
    }

}
