package com.danwalkerdev.statement.email;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Properties;

class ConfigurationService {

    String username() {
        return System.getProperty("mail.user");
    }

    String password() {
        return System.getProperty("mail.password");
    }

    InternetAddress to() {
        try {
            return new InternetAddress(System.getProperty("mail.to"));
        } catch (AddressException e) {
            throw new ConfigurationException(e);
        }
    }

    InternetAddress from() {
        try {
            return new InternetAddress(System.getProperty("mail.from"));
        } catch (AddressException e) {
            throw new ConfigurationException(e);
        }
    }

    Properties mailProperties() {
        Properties prop = new Properties();

        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 587);
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        return prop;
    }
}
