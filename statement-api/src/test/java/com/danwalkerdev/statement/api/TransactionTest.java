package com.danwalkerdev.statement.api;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void verify_to_string_format() {
        Transaction t = new Transaction();
        t.setDate(LocalDate.of(2021, Month.APRIL, 23));
        t.setValue(1.520008);
        t.setDescription("foo");

        assertEquals("2021-04-23  ::  1.52  ::  foo", t.toString());
    }

}