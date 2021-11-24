package com.danwalkerdev.statement.natwest;

import com.danwalkerdev.statement.api.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class TransactionConverter {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM uuuu", Locale.US);
    private static final int ELIDE_LIMIT = 32;
    private static final String ELLIPSIS = "...";
    private static final List<NatwestType> MAPPABLE_TYPES = List.of(NatwestType.PURCHASE, NatwestType.REFUND, NatwestType.FEE);

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
        return MAPPABLE_TYPES.contains(natwestType);
    }

    private String processDescription(String string) {
        String noquote = stripLeadingQuote(string);
        return string.length() > ELIDE_LIMIT ? elide(noquote) : noquote;
    }

    private String stripLeadingQuote(String string) {
        if (string.startsWith("'")) return string.substring(1);
        return string;
    }

    private String elide(String string) {
        return string.substring(0, ELIDE_LIMIT - ELLIPSIS.length()) + ELLIPSIS;
    }

}
