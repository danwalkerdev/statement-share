package com.danwalkerdev.statement.sheet;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigurationService {

    public Path getOutputPath() {
        String path = System.getProperty("statement.output");
        if (path == null || path.isBlank()) throw new IllegalArgumentException();
        return Paths.get(System.getProperty("statement.output"));
    }
}
