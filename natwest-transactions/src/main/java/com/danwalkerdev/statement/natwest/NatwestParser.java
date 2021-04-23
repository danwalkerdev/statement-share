package com.danwalkerdev.statement.natwest;

import com.danwalkerdev.statement.api.BankParser;
import com.danwalkerdev.statement.api.Transaction;
import com.danwalkerdev.statement.exception.ParserException;
import com.danwalkerdev.statement.exception.StatementException;
import com.opencsv.CSVParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NatwestParser implements BankParser {

    private final CSVParser csvParser = new CSVParser();
    private final TransactionConverter transactionConverter = new TransactionConverter();

    @Override
    public List<Transaction> parse(Path path) throws StatementException {
        try {
            return Files.lines(path)
                    .filter(Predicate.not(String::isBlank))
                    .filter(Predicate.not(isHeaderRow()))
                    .map(this::parseLine)
                    .filter(Predicate.not(isSummaryRow()))
                    .map(transactionConverter::mapTransaction)
                    .collect(Collectors.toList());
        } catch (IOException | ParserException e) {
            throw new StatementException(e);
        }
    }

    private String[] parseLine(String line) {
        try {
            return csvParser.parseLine(line);
        } catch (IOException e) {
            throw new ParserException(e);
        }
    }

    private Predicate<String> isHeaderRow() {
        return s -> s.chars().noneMatch(Character::isDigit);
    }

    private Predicate<String[]> isSummaryRow() {
        return a -> a[1].isBlank();
    }

    @Override
    public String identifier() {
        return "natwest-mastercard";
    }
}
