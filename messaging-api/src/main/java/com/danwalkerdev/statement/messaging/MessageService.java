package com.danwalkerdev.statement.messaging;

import java.util.stream.Stream;

public interface MessageService {

    void send(Stream<String> stream);
}
