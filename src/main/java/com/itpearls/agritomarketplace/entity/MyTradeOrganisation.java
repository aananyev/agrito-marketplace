package com.itpearls.agritomarketplace.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@JmixEntity
@Entity
public class MyTradeOrganisation extends ProductByer {
    @Column(name = "MY_TRADE_ORGANISATION")
    private Boolean myTradeOrganisation;

    @Column(name = "PERSONELL_COUNT")
    private Integer personellCount;

    @Column(name = "SALES_VOLUME_YEAR", precision = 19, scale = 2)
    private BigDecimal salesVolumeYear;

    public BigDecimal getSalesVolumeYear() {
        return salesVolumeYear;
    }

    public void setSalesVolumeYear(BigDecimal salesVolumeYear) {
        this.salesVolumeYear = salesVolumeYear;
    }

    public Integer getPersonellCount() {
        return personellCount;
    }

    public void setPersonellCount(Integer personellCount) {
        this.personellCount = personellCount;
    }

    public Boolean getMyTradeOrganisation() {
        return myTradeOrganisation;
    }

    public void setMyTradeOrganisation(Boolean myTradeOrganisation) {
        this.myTradeOrganisation = myTradeOrganisation;
    }
}