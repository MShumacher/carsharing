package com.training.carsharing.model.impl;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Message extends BaseEntity {

    @Column
    private String message;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Ad.class)
    private Ad ad;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    private UserAccount sender;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    private UserAccount recipient;

    @Column
    private boolean viewed;

    @Override
    public String toString() {
        return "Message{" + super.toString() +
                ", message='" + message + '\'' +
                ", ad=" + ad.getBody() +
                ", senderId=" + sender.getId() +
                ", recipientId=" + recipient.getId() +
                ", viewed=" + viewed +
                '}';
    }
}
