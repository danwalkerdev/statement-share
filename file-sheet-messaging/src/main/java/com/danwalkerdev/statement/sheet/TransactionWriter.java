package com.danwalkerdev.statement.sheet;

import com.danwalkerdev.statement.api.Transaction;

import java.time.format.DateTimeFormatter;

public class TransactionWriter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM");
    private static final String FORMAT_STRING = "%s\t%.2f\t%s%n";

    public String write(Transaction transaction) {
        return String.format(FORMAT_STRING,
                DATE_FORMATTER.format(transaction.getDate()),
                transaction.getValue(),
                transaction.getDescription());
    }
}
