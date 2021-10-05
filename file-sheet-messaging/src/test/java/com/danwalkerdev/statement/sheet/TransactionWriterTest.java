package com.danwalkerdev.statement.sheet;

import com.danwalkerdev.statement.api.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionWriterTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private final TransactionWriter writer = new TransactionWriter();

    @Test
    void transaction_format_is_as_expected() {
        final LocalDate date = LocalDate.of(2021, Month.MAY, 4);
        final Transaction t = makeTransaction(5.00, "eggs", date);

        String result = writer.write(t);

        assertEquals("04/05\t5.00\teggs" + LINE_SEPARATOR, result);
    }

    private Transaction makeTransaction(double amount, String description, LocalDate date) {
        Transaction t = new Transaction();
        t.setValue(amount);
        t.setDescription(description);
        t.setDate(date);
        return t;
    }
}
