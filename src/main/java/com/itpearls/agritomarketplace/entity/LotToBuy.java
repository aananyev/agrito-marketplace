package com.itpearls.agritomarketplace.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@JmixEntity
@Entity
public class LotToBuy extends TradingLot {
    @Column(name = "BUY")
    private Boolean buy;

    public Boolean getBuy() {
        return buy;
    }

    public void setBuy(Boolean buy) {
        this.buy = buy;
    }
}