package com.danwalkerdev.statement.natwest;

import com.danwalkerdev.statement.api.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class TransactionConverterTest {

    private static final String[] DATA_LINE = {"08 Mar 2021","Purchase","\"'IZ *The Farm*\"","6.50","","\"'J DOE\"","\"'111111******1111\"",""};
    private static final String[] REFUND_LINE = {"08 Mar 2021","Purchase","\"'IZ *The Farm*\"","-6.50","","\"'J DOE\"","\"'111111******1111\"",""};
    private static final LocalDate TRANSACTION_DATE = LocalDate.of(2021, Month.MARCH, 8);
    private static final double DELTA = 0.005d;
    private final TransactionConverter converter = new TransactionConverter();

    @Test
    void data_line_parsed_correctly() {
        Transaction transaction = converter.mapTransaction(DATA_LINE);
        assertEquals(TRANSACTION_DATE, transaction.getDate());
        assertEquals("\"'IZ *The Farm*\"", transaction.getDescription());
        assertEquals(6.50d, transaction.getValue(), DELTA);

    }

    @Test
    void refund_line_parsed_correctly() {
        Transaction transaction = converter.mapTransaction(REFUND_LINE);
        assertEquals(TRANSACTION_DATE, transaction.getDate());
        assertEquals("\"'IZ *The Farm*\"", transaction.getDescription());
        assertEquals(-6.50d, transaction.getValue(), DELTA);
    }

}