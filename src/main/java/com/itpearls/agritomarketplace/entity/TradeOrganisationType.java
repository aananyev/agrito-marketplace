package com.itpearls.agritomarketplace.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JmixEntity
@Table(name = "TRADE_ORGANISATION_TYPE")
@Entity
public class TradeOrganisationType {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "TRADE_ORGANISATION_TYPE", nullable = false, length = 32)
    @NotNull
    private String tradeOrganisationType;

    public String getTradeOrganisationType() {
        return tradeOrganisationType;
    }

    public void setTradeOrganisationType(String tradeOrganisationType) {
        this.tradeOrganisationType = tradeOrganisationType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}