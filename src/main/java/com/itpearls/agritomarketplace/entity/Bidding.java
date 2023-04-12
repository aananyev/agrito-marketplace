package com.itpearls.agritomarketplace.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@JmixEntity
@Table(name = "BIDDING", indexes = {
        @Index(name = "IDX_BIDDING_TRADING_LOT", columnList = "TRADING_LOT_ID"),
        @Index(name = "IDX_BIDDING_COUTERPARTY", columnList = "COUTERPARTY_ID")
})
@Entity
public class Bidding {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "TRADING_LOT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private TradingLot tradingLot;

    @Column(name = "PROPOSAL_COST", precision = 19, scale = 2)
    private BigDecimal proposalCost;

    @JoinColumn(name = "COUTERPARTY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Counterparty couterparty;

    @Column(name = "TRADE_ROLE", length = 64)
    private String tradeRole;

    @Column(name = "BIDDING_STATUS", length = 64)
    private String biddingStatus;

    public BiddingStatus getBiddingStatus() {
        return biddingStatus == null ? null : BiddingStatus.fromId(biddingStatus);
    }

    public void setBiddingStatus(BiddingStatus biddingStatus) {
        this.biddingStatus = biddingStatus == null ? null : biddingStatus.getId();
    }

    public TradeRole getTradeRole() {
        return tradeRole == null ? null : TradeRole.fromId(tradeRole);
    }

    public void setTradeRole(TradeRole tradeRole) {
        this.tradeRole = tradeRole == null ? null : tradeRole.getId();
    }

    public Counterparty getCouterparty() {
        return couterparty;
    }

    public void setCouterparty(Counterparty couterparty) {
        this.couterparty = couterparty;
    }

    public BigDecimal getProposalCost() {
        return proposalCost;
    }

    public void setProposalCost(BigDecimal proposalCost) {
        this.proposalCost = proposalCost;
    }

    public TradingLot getTradingLot() {
        return tradingLot;
    }

    public void setTradingLot(TradingLot tradingLot) {
        this.tradingLot = tradingLot;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}