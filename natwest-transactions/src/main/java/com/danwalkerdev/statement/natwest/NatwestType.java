package com.danwalkerdev.statement.natwest;

import com.danwalkerdev.statement.api.TransactionType;

import java.util.Arrays;

public enum NatwestType implements TransactionType {

    PURCHASE("Purchase"),
    REFUND("Refund"),
    PAYMENT("Payment"),
    BALANCE("");

    private final String description;

    NatwestType(String description) {
        this.description = description;
    }

    public static NatwestType of(String description) {
        return Arrays.stream(NatwestType.values())
                .filter(nt -> nt.description.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot parse " + description));
    }
}
