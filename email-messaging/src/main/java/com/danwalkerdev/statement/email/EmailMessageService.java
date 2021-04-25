package com.danwalkerdev.statement.email;

import com.danwalkerdev.statement.messaging.MessageException;
import com.danwalkerdev.statement.messaging.MessageService;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmailMessageService implements MessageService {

    private final ConfigurationService service;

    public EmailMessageService(ConfigurationService service) {
        this.service = service;
    }

    @Override
    public <T> void send(Stream<T> stream) {
        Session session = Session.getInstance(service.mailProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(service.username(), service.password());
            }
        });
        try {
            sendMessage(session, stream);
        } catch (MessagingException e) {
            throw new MessageException(e);
        }
    }

    private <T> void sendMessage(Session session, Stream<T> stream) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(service.from());
        message.setRecipient(Message.RecipientType.TO, service.to());

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(stream
                .map(Objects::toString)
                .collect(Collectors.joining("\\n")), "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }
}
