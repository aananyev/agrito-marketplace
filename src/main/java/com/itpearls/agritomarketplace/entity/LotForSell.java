package com.itpearls.agritomarketplace.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@JmixEntity
@Entity
public class LotForSell extends TradingLot {
    @Column(name = "SELL")
    private Boolean sell;

    public Boolean getSell() {
        return sell;
    }

    public void setSell(Boolean sell) {
        this.sell = sell;
    }
}