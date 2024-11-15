package com.epam.backend.core.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    private String bankCardNumber;
    private LocalDate startDate;

    public static Subscription of(String bankCardNumber, LocalDate startDate) {
        return new Subscription(bankCardNumber, startDate);
    }
}
