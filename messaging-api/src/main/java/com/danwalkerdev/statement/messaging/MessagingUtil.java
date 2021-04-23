package com.danwalkerdev.statement.messaging;

import java.util.Iterator;
import java.util.ServiceLoader;

public class MessagingUtil {

    private ServiceLoader<MessagingProvider> loader = ServiceLoader.load(MessagingProvider.class);

    public Iterator<MessagingProvider> providers() {
        return loader.iterator();
    }
}
