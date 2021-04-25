package com.danwalkerdev.statement.messaging;

public class MessageException extends RuntimeException {

    public MessageException(Throwable cause) {
        super("Error when messaging", cause);
    }
}
