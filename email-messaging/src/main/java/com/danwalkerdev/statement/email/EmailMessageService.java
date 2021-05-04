package com.danwalkerdev.statement.email;

import com.danwalkerdev.statement.api.Transaction;
import com.danwalkerdev.statement.messaging.MessageException;
import com.danwalkerdev.statement.messaging.MessageService;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.stream.Stream;

public class EmailMessageService implements MessageService {

    private final ConfigurationService service;
    private final HtmlContentCreator contentCreator;

    public EmailMessageService(ConfigurationService service) {
        this.service = service;
        this.contentCreator = new HtmlContentCreator();
    }

    @Override
    public void send(Stream<Transaction> stream) {
        Session session = Session.getInstance(service.mailProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(service.username(), service.password());
            }
        });
        try {
            String content = contentCreator.make(stream);
            sendMessage(session, content);
        } catch (MessagingException e) {
            throw new MessageException(e);
        }
    }

    private void sendMessage(Session session, String content) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setSubject("Test transactions");
        message.setFrom(service.from());
        message.setRecipient(Message.RecipientType.TO, service.to());

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(content, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }

}
