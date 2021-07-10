package com.danwalkerdev.statement;

import com.danwalkerdev.statement.api.StatementUtil;
import com.danwalkerdev.statement.api.Transaction;
import com.danwalkerdev.statement.messaging.MessagingUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static final String USAGE = "Please provide one argument, either the directory containing the files " +
            "you wish to parse, or the actual file path itself";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(USAGE);
            System.exit(1);
        }
        Path path = Paths.get(args[0]);
        List<Transaction> transactions = new ArrayList<>();

        // parse all transactions from all providers
        new StatementUtil().providers().forEachRemaining(parserProvider -> transactions.addAll(new ParserHelper(parserProvider.getBankParser(), path).doParse()));
        // send transactions via all provided messaging services
        new MessagingUtil().providers().forEachRemaining(messagingProvider -> messagingProvider.getService().send(transactions));

    }
}
