package com.itpearls.agritomarketplace.entity;

import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@JmixEntity
@Entity
public class ProductByer extends Counterparty {
    @Column(name = "BYERS")
    private Boolean byers;

    public Boolean getByers() {
        return byers;
    }

    public void setByers(Boolean byers) {
        this.byers = byers;
    }

    @InstanceName
    @DependsOnProperties({"counterpartyName"})
    public String getInstanceName() {
        return String.format("%s", getCounterpartyName());
    }
}