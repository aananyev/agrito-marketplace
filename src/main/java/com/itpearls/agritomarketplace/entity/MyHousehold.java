package com.itpearls.agritomarketplace.entity;

import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@JmixEntity
@Entity
public class MyHousehold extends AgriculturalManufacturer {
    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    @Column(name = "MY_HOUSEHOLD")
    private Boolean myHousehold;

    @Column(name = "START_DATE_ACTIVITY")
    @Temporal(TemporalType.DATE)
    private Date startDateActivity;

    @Column(name = "ACREAGE", precision = 19, scale = 2)
    private BigDecimal acreage;

    @JoinColumn(name = "TYPE_ACTIVITY_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TypeActivity typeActivity;

    @Column(name = "NUMBER_EMPLOYEES")
    private Integer numberEmployees;

    @Column(name = "UNITS_EQUIPMENT")
    private Integer unitsEquipment;

    @Column(name = "COMMENT_")
    @Lob
    private String comment;

    @JoinColumn(name = "OWNER_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User owner;

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @InstanceName
    @DependsOnProperties({"counterpartyName"})
    public String getInstanceName() {
        return String.format("%s", getCounterpartyName());
    }
}