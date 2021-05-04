package com.danwalkerdev.statement.api;

import java.time.LocalDate;

public class Transaction {

    private LocalDate date;
    private String description;
    private double value;
    private String account;
    private TransactionType type;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return date.toString() + "  ::  " + String.format("%.2f", value) + "  ::  " + description;
    }
}
