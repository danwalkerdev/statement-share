package com.danwalkerdev.statement.sheet;

import com.danwalkerdev.statement.messaging.MessageService;
import com.danwalkerdev.statement.messaging.MessagingProvider;

public class FileSheetMessagingProvider implements MessagingProvider {

    @Override
    public MessageService getService() {
        return new FileSheetMessageService();
    }
}
