package com.danwalkerdev.statement.api;

import com.danwalkerdev.statement.exception.StatementException;

import java.nio.file.Path;
import java.util.List;

public interface BankParser {

    List<Transaction> parse(Path path) throws StatementException;
    String identifier();
}
