package ru.mephi.gpus_sender.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Messages {

    List<String> emails;
    String productId;
    double oldCost;
    double newCost;

    public Messages setEmails(List<String> emails) {
        this.emails = emails;
        return this;
    }

    public Messages setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public Messages setOldCost(double oldCost) {
        this.oldCost = oldCost;
        return this;
    }

    public Messages setNewCost(double newCost) {
        this.newCost = newCost;
        return this;
    }
}
