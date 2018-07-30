package com.training.carsharing.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public UserAccount getSender() {
        return sender;
    }

    public void setSender(UserAccount sender) {
        this.sender = sender;
    }

    public UserAccount getRecipient() {
        return recipient;
    }

    public void setRecipient(UserAccount recipient) {
        this.recipient = recipient;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

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
