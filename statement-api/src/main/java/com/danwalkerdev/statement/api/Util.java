package com.danwalkerdev.statement.api;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Util {
    private final ServiceLoader<ParserProvider> loader = ServiceLoader.load(ParserProvider.class);

    public Iterator<ParserProvider> providers() {
        return loader.iterator();
    }
}
