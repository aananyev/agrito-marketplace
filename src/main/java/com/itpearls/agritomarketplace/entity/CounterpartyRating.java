package com.itpearls.agritomarketplace.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "COUNTERPARTY_RATING", indexes = {
        @Index(name = "IDX_COUNTERPARTY_RATING_USER", columnList = "USER_ID"),
        @Index(name = "IDX_COUNTERPARTY_RATING_COUNTERPARTY", columnList = "COUNTERPARTY_ID")
})
@Entity
public class CounterpartyRating {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @JoinColumn(name = "COUNTERPARTY_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Counterparty counterparty;

    @NotNull
    @JoinColumn(name = "USER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @DecimalMax(message = "{msg://com.itpearls.agritomarketplace.entity/CounterpartyRating.rating.validation.DecimalMax}", value = "5")
    @DecimalMin(message = "{msg://com.itpearls.agritomarketplace.entity/CounterpartyRating.rating.validation.DecimalMin}", value = "1")
    @NotNull
    @Column(name = "RATING", nullable = false)
    private BigDecimal rating;

    @Column(name = "RATING_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date ratingDate;

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public Date getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(Date ratingDate) {
        this.ratingDate = ratingDate;
    }

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}