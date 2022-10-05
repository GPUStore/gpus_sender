package ru.mephi.gpus_sender.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.mephi.gpus_sender.entity.Messages;
import ru.mephi.gpus_sender.service.MailService;
import ru.mephi.gpus_sender.service.TelegramService;

@RestController
@RequiredArgsConstructor
public class SenderController {

    private final MailService mailService;
    private final TelegramService telegramService;

    public void send(Messages messages) {

    }
}
