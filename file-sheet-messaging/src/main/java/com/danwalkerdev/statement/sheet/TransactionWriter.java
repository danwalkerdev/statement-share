package com.danwalkerdev.statement.sheet;

import com.danwalkerdev.statement.api.Transaction;

import java.time.format.DateTimeFormatter;

public class TransactionWriter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM");
    private static final String FORMAT_STRING = "%.2f\t%s\t%s%n";

    public String write(Transaction transaction) {
        return String.format(FORMAT_STRING,
                transaction.getValue(),
                transaction.getDescription(),
                DATE_FORMATTER.format(transaction.getDate()));
    }
}
