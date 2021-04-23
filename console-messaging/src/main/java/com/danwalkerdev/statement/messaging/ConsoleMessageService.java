package com.danwalkerdev.statement.messaging;

import java.util.stream.Stream;

public class ConsoleMessageService implements MessageService {

    @Override
    public <T> void send(Stream<T> stream) {
        stream.forEach(System.out::println);
    }
}
