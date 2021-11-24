package com.danwalkerdev.statement.natwest;

import com.danwalkerdev.statement.api.Transaction;
import com.danwalkerdev.statement.exception.StatementException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NatwestParserTest {

    private static Path path;
    private final NatwestParser parser = new NatwestParser();

    @BeforeAll
    static void setup() throws URISyntaxException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("natwest-mastercard.csv");
        assertNotNull(resource, "Resource");
        path = Paths.get(resource.toURI());
    }

    @Test
    void test_example_parse() throws StatementException {
        parser.parse(path);
    }

    @Test
    void description_leading_quote_is_removed() throws StatementException {
        List<Transaction> transactions = parser.parse(path);
        transactions.stream()
                .map(Transaction::getDescription)
                .forEach(description -> assertFalse(description.startsWith("'"), description + ": starts with a quote"));
    }

    @Test
    void faster_payment_line_not_included() throws StatementException {
        List<Transaction> transactions = parser.parse(path);
        assertTrue(transactions.stream()
                .map(Transaction::getType)
                .map(NatwestType.class::cast)
                .noneMatch(nt -> nt == NatwestType.PAYMENT));
    }

    @Test
    void balance_line_not_included() throws StatementException {
        List<Transaction> transactions = parser.parse(path);
        assertTrue(transactions.stream()
                .map(Transaction::getType)
                .map(NatwestType.class::cast)
                .noneMatch(nt -> nt == NatwestType.BALANCE));
    }

    @Test
    void mappable_transactions_are_mapped() throws StatementException {
        List<Transaction> transactions = parser.parse(path);
        assertEquals(4, transactions.size());
        assertEquals(1, transactions.stream()
                .map(Transaction::getType)
                .filter(NatwestType.FEE::equals)
                .count());
        assertEquals(3, transactions.stream()
                .map(Transaction::getType)
                .filter(NatwestType.PURCHASE::equals)
                .count());
    }


    // use a new path/resource. Does work though
//    @Test
//    void test_csvParser_IOException_throws_StatementException() {
//        assertThrows(StatementException.class, () -> parser.parse(path));
//    }

}
