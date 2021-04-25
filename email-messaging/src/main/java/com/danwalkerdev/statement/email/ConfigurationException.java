package com.danwalkerdev.statement.email;

public class ConfigurationException extends RuntimeException {

    public ConfigurationException(Throwable cause) {
        super("Unable to configure email settings", cause);
    }
}
