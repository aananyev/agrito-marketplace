package com.itpearls.agritomarketplace.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@JmixEntity
@Entity
public class DealRequestPurchaseBuy extends DealRequest {
    @JoinColumn(name = "LOT_FOR_SELL_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private LotForSell lotForSell;

    public LotForSell getLotForSell() {
        return lotForSell;
    }

    public void setLotForSell(LotForSell lotForSell) {
        this.lotForSell = lotForSell;
    }
}