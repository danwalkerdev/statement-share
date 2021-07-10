package com.danwalkerdev.statement.messaging;

import com.danwalkerdev.statement.api.Transaction;

import java.util.Collection;

public interface MessageService {

    void send(Collection<Transaction> transactions);
}
