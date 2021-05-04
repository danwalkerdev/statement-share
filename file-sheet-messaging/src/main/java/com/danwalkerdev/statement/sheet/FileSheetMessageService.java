package com.danwalkerdev.statement.sheet;

import com.danwalkerdev.statement.api.Transaction;
import com.danwalkerdev.statement.messaging.MessageService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class FileSheetMessageService implements MessageService {

    private final ConfigurationService configurationService = new ConfigurationService();
    private final TransactionWriter transactionWriter = new TransactionWriter();

    @Override
    public void send(Stream<Transaction> transactionStream) {
        Path path = configurationService.getOutputPath();
        try {
            process(transactionStream, path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void process(Stream<Transaction> transactionStream, Path path) throws IOException {
        if (Files.exists(path)) {
            Files.deleteIfExists(path);
        } else {
            Files.createDirectories(path.getParent());
        }

        try (var fos = Files.newOutputStream(path, StandardOpenOption.CREATE_NEW)) {
            transactionStream
                    .map(transactionWriter::write)
                    .forEach(tryWrite(fos));
        }
    }

    private Consumer<String> tryWrite(OutputStream os) {
        return s -> {
            try {
                os.write(s.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

}
