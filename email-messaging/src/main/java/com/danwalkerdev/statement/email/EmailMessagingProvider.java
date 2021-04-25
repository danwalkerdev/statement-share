package com.danwalkerdev.statement.email;

import com.danwalkerdev.statement.messaging.MessageService;
import com.danwalkerdev.statement.messaging.MessagingProvider;

public class EmailMessagingProvider implements MessagingProvider {

    @Override
    public MessageService getService() {
        return new EmailMessageService(new ConfigurationService());
    }
}
