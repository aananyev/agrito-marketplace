package com.itpearls.agritomarketplace.entity;

import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;

@JmixEntity
@Entity
public class ProductByer extends Counterparty {
    @Column(name = "BYERS")
    private Boolean byers;

    @JoinColumn(name = "TRADE_ORGANISATION_TYPE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private TradeOrganisationType tradeOrganisationType;

    public TradeOrganisationType getTradeOrganisationType() {
        return tradeOrganisationType;
    }

    public void setTradeOrganisationType(TradeOrganisationType tradeOrganisationType) {
        this.tradeOrganisationType = tradeOrganisationType;
    }

    public Boolean getByers() {
        return byers;
    }

    public void setTrader(Boolean trader) {
        this.byers= trader;
    }

    @InstanceName
    @DependsOnProperties({"counterpartyName"})
    public String getInstanceName() {
        return String.format("%s", getCounterpartyName());
    }
}