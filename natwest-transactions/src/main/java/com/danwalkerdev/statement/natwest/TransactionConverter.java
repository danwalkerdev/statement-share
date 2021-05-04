package com.danwalkerdev.statement.natwest;

import com.danwalkerdev.statement.api.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionConverter {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM uuuu");
    private static final int TRUNCATE_LIMIT = 24;
    private static final String ELLIPSIS = "...";

    Transaction mapTransaction(String[] parsedLine) {
        if (shouldNotBeMapped(parsedLine)) return null;

        Transaction transaction = new Transaction();
        transaction.setDate(DATE_FORMAT.parse(parsedLine[0], LocalDate::from));
        transaction.setType(NatwestType.of(parsedLine[1]));
        transaction.setDescription(processDescription(parsedLine[2]));
        transaction.setValue(Double.parseDouble(parsedLine[3]));
        return transaction;
    }

    private boolean shouldNotBeMapped(String[] parsedLine) {
        NatwestType natwestType = NatwestType.of(parsedLine[1]);
        return !isMappable(natwestType);
    }

    private boolean isMappable(NatwestType natwestType) {
        return natwestType == NatwestType.PURCHASE || natwestType == NatwestType.REFUND;
    }

    private String processDescription(String string) {
        String noquote = stripLeadingQuote(string);
        return truncate(noquote);
    }

    private String stripLeadingQuote(String string) {
        if (string.startsWith("'")) return string.substring(1);
        return string;
    }

    private String truncate(String string) {
        if (string.length() > TRUNCATE_LIMIT) {
            return string.substring(0, TRUNCATE_LIMIT - ELLIPSIS.length()) + ELLIPSIS;
        } else {
            return string;
        }
    }

}
