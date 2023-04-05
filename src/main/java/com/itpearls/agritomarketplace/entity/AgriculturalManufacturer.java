package com.itpearls.agritomarketplace.entity;

import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@JmixEntity
@Entity
public class AgriculturalManufacturer extends Counterparty {
    @Column(name = "MANUFACTURER")
    private Boolean manufacturer;

    public Boolean getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Boolean manufacturer) {
        this.manufacturer = manufacturer;
    }

    @InstanceName
    @DependsOnProperties({"counterpartyName"})
    public String getInstanceName() {
        return String.format("%s", getCounterpartyName());
    }
}