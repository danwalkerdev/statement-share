package com.danwalkerdev.statement.email;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HtmlContentCreatorTest {

    private final HtmlContentCreator creator = new HtmlContentCreator();

    @Test
    void check_template_values_subsituted() throws IOException {
        final String eggman = "I am the egg man";
        final String eggmen = "We are the egg men";
        final String walrus = "I am the Walrus";
        String content = creator.make(Stream.of(eggman, eggmen, walrus));

        assertTrue(content.contains(eggman), "Content should contain '" + eggman + "'");
        assertTrue(content.contains(eggmen), "Content should contain '" + eggmen + "'");
        assertTrue(content.contains(walrus), "Content should contain '" + walrus + "'");

        Files.deleteIfExists(Paths.get("build/output.html"));
        Files.write(Paths.get("build/output.html"), content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE_NEW);

    }

}