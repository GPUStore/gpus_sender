package ru.mephi.gpus_sender.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.mephi.gpus_sender.entity.Client;
import ru.mephi.gpus_sender.entity.Messages;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramService {
    private static final String PATH_TO_TELEGRAM_FORMAT = "gpus_sender/src/main/resources/messages/telegram.html";
    private final SenderBot senderBot;
    @Value("${outbound.get-product}")
    private String getProductUrl;
    @Value("${outbound.unsubscribe}")
    private String unsubscribeUrl;

    public void sendTelegramMessages(Messages messages) {
        try {
            String productId = messages.getProductId();
            double oldCost = messages.getOldCost();
            double newCost = messages.getNewCost();
            for (Client client : messages.getClients()) {
                String message = createMessage(productId, oldCost, newCost, client.getId());
                SendMessage telegramMessage = new SendMessage(client.getChatId(),message);
                telegramMessage.setParseMode("HTML");
                senderBot.sendTextByChatId(telegramMessage);
            }
        } catch (IOException e) {
            log.error("File for email message not found!", e);
        }
    }

    private String createMessage(String productId,
                                 double oldCost,
                                 double newCost,
                                 String clientId) throws IOException {
        return String.format(
                getFormat(),
                String.format(getProductUrl, productId),
                oldCost,
                newCost,
                String.format(unsubscribeUrl, clientId, productId),
                String.format(unsubscribeUrl, clientId, "")
        );
    }

    private String getFormat() throws IOException {
        return Files.readString(Path.of(PATH_TO_TELEGRAM_FORMAT));
    }
}
