package ru.mephi.gpus_sender.service;

import org.springframework.stereotype.Service;
import ru.mephi.gpus_sender.entity.Messages;

@Service
public class MailService extends Sender{

    @Override
    protected void send(Messages messages) {

    }

}
