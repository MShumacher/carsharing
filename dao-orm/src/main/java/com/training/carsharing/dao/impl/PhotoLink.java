package com.training.carsharing.dao.impl;

import com.training.carsharing.dao.IAd;
import com.training.carsharing.dao.IPhotoLink;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class PhotoLink extends BaseEntity implements IPhotoLink {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Ad.class)
    private IAd ad;

    @Column
    private String link;

    @Override
    public IAd getAd() {
        return ad;
    }

    @Override
    public void setAd(IAd ad) {
        this.ad = ad;
    }

    @Override
    public String getLink() {
        return link;
    }

    @Override
    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "PhotoLink{" +
                "id='" + getId() + '\'' +
                ", ad=" + ad +
                ", link='" + link + '\'' +
                '}';
    }
}
