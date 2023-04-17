package com.itpearls.agritomarketplace.entity;

import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@JmixEntity
@Entity
public class MyHousehold extends AgriculturalManufacturer {

    @Column(name = "MY_HOUSEHOLD")
    private Boolean myHousehold;

    @Column(name = "START_DATE_ACTIVITY")
    @Temporal(TemporalType.DATE)
    private Date startDateActivity;

    @Column(name = "ACREAGE", precision = 19, scale = 2)
    private BigDecimal acreage;

    @JoinColumn(name = "TYPE_ACTIVITY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private TypeActivity typeActivity;

    @Column(name = "NUMBER_EMPLOYEES")
    private Integer numberEmployees;

    @Column(name = "UNITS_EQUIPMENT")
    private Integer unitsEquipment;

    @Column(name = "COMMENT_")
    @Lob
    private String comment;

    public Date getStartDateActivity() {
        return startDateActivity;
    }

    public void setStartDateActivity(Date startDateActivity) {
        this.startDateActivity = startDateActivity;
    }

    public Integer getUnitsEquipment() {
        return unitsEquipment;
    }

    public void setUnitsEquipment(Integer unitsEquipment) {
        this.unitsEquipment = unitsEquipment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getMyHousehold() {
        return myHousehold;
    }

    public void setMyHousehold(Boolean myHousehold) {
        this.myHousehold = myHousehold;
    }

    public Integer getNumberEmployees() {
        return numberEmployees;
    }

    public void setNumberEmployees(Integer numberEmployees) {
        this.numberEmployees = numberEmployees;
    }

    public TypeActivity getTypeActivity() {
        return typeActivity;
    }

    public void setTypeActivity(TypeActivity typeActivity) {
        this.typeActivity = typeActivity;
    }

    public BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
    }

    @InstanceName
    @DependsOnProperties({"counterpartyName"})
    public String getInstanceName() {
        return String.format("%s", getCounterpartyName());
    }
}