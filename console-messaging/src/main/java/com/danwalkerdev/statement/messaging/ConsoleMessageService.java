package com.danwalkerdev.statement.messaging;

import com.danwalkerdev.statement.api.Transaction;

import java.util.Collection;

public class ConsoleMessageService implements MessageService {

    @Override
    public void send(Collection<Transaction> transactions) {
        transactions.forEach(System.out::println);
    }
}
