package com.danwalkerdev.statement.messaging;

import java.util.stream.Stream;

public interface MessageService {

    <T> void send(Stream<T> stream);
}
