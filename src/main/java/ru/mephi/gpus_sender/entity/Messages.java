package ru.mephi.gpus_sender.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Messages {

    List<Client> clients;
    String productId;
    double oldCost;
    double newCost;
}
