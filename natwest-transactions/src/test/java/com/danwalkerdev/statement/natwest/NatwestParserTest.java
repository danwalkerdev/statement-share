package com.danwalkerdev.statement.natwest;

import com.danwalkerdev.statement.exception.StatementException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    // use a new path/resource. Does work though
//    @Test
//    void test_csvParser_IOException_throws_StatementException() {
//        assertThrows(StatementException.class, () -> parser.parse(path));
//    }

}