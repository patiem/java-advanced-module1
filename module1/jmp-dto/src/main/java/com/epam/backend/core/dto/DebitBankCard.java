package com.epam.backend.core.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DebitBankCard extends BankCard {
    private double balance;

   public DebitBankCard(User user) {
        super( user);
    }

}
