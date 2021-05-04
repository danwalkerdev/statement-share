package com.danwalkerdev.statement.messaging;

import com.danwalkerdev.statement.api.Transaction;

import java.util.stream.Stream;

public interface MessageService {

    void send(Stream<Transaction> stream);
}
