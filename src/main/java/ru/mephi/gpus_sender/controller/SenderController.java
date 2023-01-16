package ru.mephi.gpus_sender.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mephi.gpus_sender.entity.Messages;
import ru.mephi.gpus_sender.service.EmailService;
import ru.mephi.gpus_sender.service.TelegramService;

@RestController
@RequiredArgsConstructor
public class SenderController {

    private final EmailService emailService;
    private final TelegramService telegramService;

    @PostMapping("/email")
    public void sendEmail(@RequestBody Messages messages) {
        emailService.sendEmail(messages);
    }

    @PostMapping("/telegram")
    public void sendTelegramMessage(@RequestBody Messages messages) {
        telegramService.sendTelegramMessages(messages);
    }
}
