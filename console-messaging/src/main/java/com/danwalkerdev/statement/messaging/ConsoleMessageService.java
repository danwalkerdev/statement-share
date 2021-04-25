package com.danwalkerdev.statement.messaging;

import java.util.stream.Stream;

public class ConsoleMessageService implements MessageService {

    @Override
    public void send(Stream<String> stream) {
        stream.forEach(System.out::println);
    }
}
