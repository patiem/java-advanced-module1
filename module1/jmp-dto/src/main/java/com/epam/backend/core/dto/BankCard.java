package com.epam.backend.core.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankCard {
    private String number;
    private User user;

    public BankCard (User user){
        this.user = user;
        this.number = generateUniqueNumber();
    }

    private String generateUniqueNumber() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
