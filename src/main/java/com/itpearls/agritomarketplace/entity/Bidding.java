package com.itpearls.agritomarketplace.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "BIDDING", indexes = {
        @Index(name = "IDX_BIDDING_TRADING_LOT", columnList = "TRADING_LOT_ID"),
        @Index(name = "IDX_BIDDING_COUTERPARTY", columnList = "COUTERPARTY_ID"),
        @Index(name = "IDX_BIDDING_PARENT_BIDDING_1", columnList = "PARENT_BIDDING_1"),
        @Index(name = "IDX_BIDDING_DEAL_REQUEST", columnList = "DEAL_REQUEST_ID")
})
@Entity
public class Bidding {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @JoinColumn(name = "TRADING_LOT_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private TradingLot tradingLot;

    @Column(name = "AMOUNT", precision = 19, scale = 2)
    private BigDecimal amount;

    @NotNull
    @Column(name = "PROPOSAL_COST", nullable = false, precision = 19, scale = 2)
    private BigDecimal proposalCost;

    @NotNull
    @JoinColumn(name = "COUTERPARTY_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Counterparty couterparty;

    @NotNull
    @Column(name = "TRADE_ROLE", nullable = false, length = 64)
    private String tradeRole;

    @Column(name = "BIDDING_STATUS", length = 64)
    private String biddingStatus;

    @Column(name = "COMMTN")
    @Lob
    private String comment;

    @NotNull
    @Column(name = "DATE_PROPOSAL", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateProposal;

    @JoinColumn(name = "PARENT_BIDDING_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Bidding childBidding;

    @JoinColumn(name = "PARENT_BIDDING_1")
    @ManyToOne(fetch = FetchType.LAZY)
    private Bidding parentBidding;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "DEAL_REQUEST_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private DealRequest dealRequest;

    public DealRequest getDealRequest() {
        return dealRequest;
    }

    public void setDealRequest(DealRequest dealRequest) {
        this.dealRequest = dealRequest;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Bidding getParentBidding() {
        return parentBidding;
    }

    public void setParentBidding(Bidding parentBidding) {
        this.parentBidding = parentBidding;
    }

    public Bidding getChildBidding() {
        return childBidding;
    }

    public void setChildBidding(Bidding childBidding) {
        this.childBidding = childBidding;
    }

    public Date getDateProposal() {
        return dateProposal;
    }

    public void setDateProposal(Date dateProposal) {
        this.dateProposal = dateProposal;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

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