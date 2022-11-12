package ru.mephi.gpus_sender.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.mephi.gpus_sender.entity.Messages;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    private static final String THEME_TEXT = "Снижение цены на продукте [GPUStore]";
    private static final String PATH_TO_EMAIL_FORMAT = "src/main/resources/messages/email.html";
    @Value("${spring.mail.sender.text}")
    private String senderText;
    @Value("${spring.mail.sender.email}")
    private String senderEmail;
    @Value("${outbound.get-product}")
    private String baseProductLink;
    private final JavaMailSender javaMailSender;

    public void sendEmail(Messages messages) {
        try {
            String message = createBaseMessage(messages);
            for (String email : messages.getEmails()) {
                send(email, message);
            }
        } catch (MessagingException e) {
            log.error("Error runtime create message!", e);
        } catch (UnsupportedEncodingException e) {
            log.error("Email message have problem!", e);
        } catch (IOException e) {
            log.error("File for email message not found!", e);
        }
    }

    private String createBaseMessage(Messages messages) throws IOException {
        return String.format(
                getFormat(),
                String.format(baseProductLink, messages.getProductId()),
                messages.getOldCost(),
                messages.getNewCost()
        );
    }

    private String getFormat() throws IOException {
        return Files.readString(Path.of(PATH_TO_EMAIL_FORMAT));
    }

    private void send(String email, String messageHtml) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(new InternetAddress(senderEmail, senderText));
        helper.setTo(email);
        helper.setSubject(THEME_TEXT);
        helper.setText(messageHtml, true);
        javaMailSender.send(message);
    }
}