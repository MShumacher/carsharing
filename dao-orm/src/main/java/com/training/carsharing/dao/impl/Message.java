package com.training.carsharing.dao.impl;

import com.training.carsharing.dao.IAd;
import com.training.carsharing.dao.IMessage;
import com.training.carsharing.dao.IUserAccount;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Message extends BaseEntity implements IMessage {

    @Column
    private String message;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    private IUserAccount sender;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
    private IUserAccount recipient;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Ad.class)
    private IAd ad;

    @Column
    private boolean viewed;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public IUserAccount getSender() {
        return sender;
    }

    @Override
    public void setSender(IUserAccount sender) {
        this.sender = sender;
    }

    @Override
    public IUserAccount getRecipient() {
        return recipient;
    }

    @Override
    public void setRecipient(IUserAccount recipient) {
        this.recipient = recipient;
    }

    @Override
    public IAd getAd() { return ad; }

    @Override
    public void setAd(IAd ad) {
        this.ad = ad;
    }

    @Override
    public boolean isViewed() {
        return viewed;
    }

    @Override
    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + getId() + '\'' +
                ", message='" + message + '\'' +
                ", sender=" + sender +
                ", recipient=" + recipient +
                ", ad=" + ad +
                ", viewed=" + viewed +
                '}';
    }
}
