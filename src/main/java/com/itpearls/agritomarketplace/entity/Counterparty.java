package com.itpearls.agritomarketplace.entity;

import io.jmix.core.FileRef;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JmixEntity
@Table(name = "COUNTERPARTY", indexes = {
        @Index(name = "IDX_COUNTERPARTY_POUNTER_PARTY_CEO", columnList = "POUNTER_PARTY_CEO_ID"),
        @Index(name = "IDX_COUNTERPARTY_COUNTER_PARTY_CHIEFF_ACCOUNTANT", columnList = "COUNTER_PARTY_CHIEFF_ACCOUNTANT_ID"),
        @Index(name = "IDX_COUNTERPARTY_COUNTERPARTY_MANAGER", columnList = "COUNTERPARTY_MANAGER_ID"),
        @Index(name = "IDX_COUNTERPARTY_TYPE_ACTIVITY", columnList = "TYPE_ACTIVITY_ID"),
        @Index(name = "IDX_COUNTERPARTY_OWNER", columnList = "OWNER_ID"),
        @Index(name = "IDX_COUNTERPARTY_AGRICULTURAL_MANUFACTURER", columnList = "AGRICULTURAL_MANUFACTURER_ID")
})
@Entity
public class Counterparty {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "COUNTERPARTY_NAME", nullable = false, length = 128)
    @NotNull
    private String counterpartyName;

    @Column(name = "COUNTERPARTY_LOGO", length = 1024)
    private FileRef counterpartyLogo;

    @Column(name = "COUNTERPARTY_ADDRESS")
    private String counterpartyAddress;

    @Column(name = "COUNTERPARTY_PHONE", length = 16)
    private String counterpartyPhone;

    @Column(name = "COUNTERPARTY_FAX", length = 16)
    private String counterpartyFax;

    @Email(message = "{msg://com.itpearls.agritomarketplace.entity/Counterparty.counterpartyEmail.validation.Email}")
    @Column(name = "COUNTERPARTY_EMAIL", length = 64)
    private String counterpartyEmail;

    @Column(name = "COUNTERPARTY_WEB_SITE", length = 64)
    private String counterpartyWebSite;

    @JoinColumn(name = "POUNTER_PARTY_CEO_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Person pounterPartyCEO;

    @JoinColumn(name = "COUNTER_PARTY_CHIEFF_ACCOUNTANT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Person counterPartyChieffAccountant;

    @JoinColumn(name = "COUNTERPARTY_MANAGER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Person counterpartyManager;

    public FileRef getCounterpartyLogo() {
        return counterpartyLogo;
    }

    public void setCounterpartyLogo(FileRef counterpartyLogo) {
        this.counterpartyLogo = counterpartyLogo;
    }

    public Person getCounterpartyManager() {
        return counterpartyManager;
    }

    public void setCounterpartyManager(Person counterpartyManager) {
        this.counterpartyManager = counterpartyManager;
    }

    public Person getCounterPartyChieffAccountant() {
        return counterPartyChieffAccountant;
    }

    public void setCounterPartyChieffAccountant(Person counterPartyChieffAccountant) {
        this.counterPartyChieffAccountant = counterPartyChieffAccountant;
    }

    public Person getPounterPartyCEO() {
        return pounterPartyCEO;
    }

    public void setPounterPartyCEO(Person pounterPartyCEO) {
        this.pounterPartyCEO = pounterPartyCEO;
    }

    public String getCounterpartyWebSite() {
        return counterpartyWebSite;
    }

    public void setCounterpartyWebSite(String counterpartyWebSite) {
        this.counterpartyWebSite = counterpartyWebSite;
    }

    public String getCounterpartyFax() {
        return counterpartyFax;
    }

    public void setCounterpartyFax(String counterpartyFax) {
        this.counterpartyFax = counterpartyFax;
    }

    public String getCounterpartyEmail() {
        return counterpartyEmail;
    }

    public void setCounterpartyEmail(String counterpartyEmail) {
        this.counterpartyEmail = counterpartyEmail;
    }

    public String getCounterpartyPhone() {
        return counterpartyPhone;
    }

    public void setCounterpartyPhone(String counterpartyPhone) {
        this.counterpartyPhone = counterpartyPhone;
    }

    public String getCounterpartyAddress() {
        return counterpartyAddress;
    }

    public void setCounterpartyAddress(String counterpartyAddress) {
        this.counterpartyAddress = counterpartyAddress;
    }

    public String getCounterpartyName() {
        return counterpartyName;
    }

    public void setCounterpartyName(String counterpartyName) {
        this.counterpartyName = counterpartyName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}