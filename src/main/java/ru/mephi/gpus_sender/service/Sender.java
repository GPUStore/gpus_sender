package ru.mephi.gpus_sender.service;

import ru.mephi.gpus_sender.entity.Messages;

public abstract class Sender {

    protected abstract void send(Messages messages);

}
