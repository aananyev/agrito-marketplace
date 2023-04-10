package com.itpearls.agritomarketplace.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@JmixEntity
@Entity
public class DealRequestSaleOffer extends DealRequest {
    @JoinColumn(name = "LOT_FOR_BUY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private LotToBuy lotForBuy;

    public LotToBuy getLotForBuy() {
        return lotForBuy;
    }

    public void setLotForBuy(LotToBuy lotForBuy) {
        this.lotForBuy = lotForBuy;
    }
}