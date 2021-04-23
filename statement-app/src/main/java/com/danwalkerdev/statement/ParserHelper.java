package com.danwalkerdev.statement;

import com.danwalkerdev.statement.api.BankParser;
import com.danwalkerdev.statement.api.Transaction;
import com.danwalkerdev.statement.exception.StatementException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ParserHelper {

    private final BankParser parser;
    private final Path path;

    public ParserHelper(BankParser parser, Path path) {
        this.parser = parser;
        this.path = path;
    }

    public List<Transaction> doParse() {
        if (Files.isDirectory(path)) {
            return aggregateDirectory(path);
        } else {
            return parser.parse(path);
        }
    }

    private List<Transaction> aggregateDirectory(Path path) {
        try {
            return Files.list(path)
                    .filter(Files::isRegularFile)
                    .filter(file -> file.toString().contains(parser.identifier()))
                    .flatMap(filePath -> parser.parse(filePath).stream())
                    .sorted(Comparator.comparing(Transaction::getDate))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new StatementException(e);
        }
    }
}
