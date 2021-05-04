package com.danwalkerdev.statement.messaging;

import com.danwalkerdev.statement.api.Transaction;

import java.util.stream.Stream;

public class ConsoleMessageService implements MessageService {

    @Override
    public void send(Stream<Transaction> stream) {
        stream.forEach(System.out::println);
    }
}
