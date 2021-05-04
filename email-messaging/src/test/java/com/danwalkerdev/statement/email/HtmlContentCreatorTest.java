package com.danwalkerdev.statement.email;

import com.danwalkerdev.statement.api.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HtmlContentCreatorTest {

    private final HtmlContentCreator creator = new HtmlContentCreator();

    @Test
    void check_template_values_subsituted() {
        final String eggman = "I am the egg man";
        final String eggmen = "We are the egg men";
        final String walrus = "I am the Walrus";
        LocalDate date = LocalDate.of(2021, Month.MAY, 4);
        String content = creator.make(Stream.of(
                makeTransaction(5.00, eggman, date),
                makeTransaction(10.00, eggmen, date),
                makeTransaction(15.00, walrus, date)));

        assertTrue(content.contains(eggman), "Content should contain '" + eggman + "'");
        assertTrue(content.contains(eggmen), "Content should contain '" + eggmen + "'");
        assertTrue(content.contains(walrus), "Content should contain '" + walrus + "'");

    }

    private Transaction makeTransaction(double amount, String description, LocalDate date) {
        Transaction t = new Transaction();
        t.setValue(amount);
        t.setDescription(description);
        t.setDate(date);
        return t;
    }

}
