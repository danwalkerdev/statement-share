package com.danwalkerdev.statement.email;

import com.danwalkerdev.statement.api.Transaction;
import com.danwalkerdev.statement.messaging.MessageException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class HtmlContentCreator {

    private static final String TEMPLATE_RESOURCE = "template/email-template.html";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
    private static final String TITLE = "\\{title}";
    private static final String DATE = "\\{date}";
    private static final String BODY_CONTENT = "\\{list-content}";

    String make(Stream<Transaction> stream) {
        String mainContent = stream
                .map(makeListElement())
                .collect(Collectors.joining());

        String template = loadTemplateFile();

        String date = LocalDate.now().format(FORMATTER);
        return template
                .replaceFirst(TITLE, "Transactions for " + date)
                .replaceFirst(DATE, date)
                .replaceFirst(BODY_CONTENT, mainContent);

    }

    private String loadTemplateFile() {
        InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream(TEMPLATE_RESOURCE);
        if (resource == null)
            throw new MessageException(new NullPointerException("Unable to find resource " + TEMPLATE_RESOURCE));

        return new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    private Function<Transaction, String> makeListElement() {
        return contents -> "<li>" + contents + "</li>\n";
    }
}
