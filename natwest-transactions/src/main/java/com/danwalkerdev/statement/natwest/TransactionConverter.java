package com.danwalkerdev.statement.natwest;

import com.danwalkerdev.statement.api.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionConverter {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM uuuu");

    Transaction mapTransaction(String[] parsedLine) {
        Transaction transaction = new Transaction();
        transaction.setDate(DATE_FORMAT.parse(parsedLine[0], LocalDate::from));
        transaction.setDescription(parsedLine[2]);
        transaction.setValue(Double.parseDouble(parsedLine[3]));
        return transaction;
    }

}
