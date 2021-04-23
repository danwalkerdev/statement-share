package com.danwalkerdev.statement.messaging;

public class ConsoleMessagingProvider implements MessagingProvider {

    @Override
    public MessageService getService() {
        return new ConsoleMessageService();
    }
}
