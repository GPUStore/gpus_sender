package ru.mephi.gpus_sender.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mephi.gpus_sender.entity.Messages;
import ru.mephi.gpus_sender.service.EmailService;

@RestController
@RequiredArgsConstructor
public class SenderController {

    private final EmailService emailService;

    @PostMapping("/email")
    public void sendEmail(@RequestBody Messages messages) {
        emailService.sendEmail(messages);
    }
}
