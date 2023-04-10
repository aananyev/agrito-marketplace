package com.itpearls.agritomarketplace.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@JmixEntity
@Table(name = "DEAL_REQUEST", indexes = {
        @Index(name = "IDX_DEAL_REQUEST_PRODUCT_BUYER", columnList = "PRODUCT_BUYER_ID"),
        @Index(name = "IDX_DEAL_REQUEST_PAYMENT_TYPE", columnList = "PAYMENT_TYPE_ID"),
        @Index(name = "IDX_DEAL_REQUEST_PRODUCT_SELLER", columnList = "PRODUCT_SELLER_ID"),
        @Index(name = "IDX_DEAL_REQUEST_LOT_FOR_SELL", columnList = "LOT_FOR_SELL_ID"),
        @Index(name = "IDX_DEAL_REQUEST_LOT_FOR_BUY", columnList = "LOT_FOR_BUY_ID")
})
@Entity
public class DealRequest {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "PRODUCT_SELLER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private AgriculturalManufacturer productSeller;

    @JoinColumn(name = "PRODUCT_BUYER_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ProductByer productBuyer;

    @Column(name = "AMOUNT", precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "PROPOSAL_COST", precision = 19, scale = 2)
    private BigDecimal proposalCost;

    @Column(name = "COMMENT_")
    @Lob
    private String comment;

    @Column(name = "DELIVERY_TERMS")
    @Lob
    private String deliveryTerms;

    @Column(name = "TERMS_PAYMENT")
    @Lob
    private String termsPayment;

    @JoinColumn(name = "PAYMENT_TYPE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PaymentType paymentType;

    @Column(name = "DEAL_REQUEST_STATUS")
    private String dealRequestStatus;

    @Column(name = "COMMENT_DEAL_REQUEST_STATUS")
    @Lob
    private String commentDealRequestStatus;

    public String getCommentDealRequestStatus() {
        return commentDealRequestStatus;
    }

    public void setCommentDealRequestStatus(String commentDealRequestStatus) {
        this.commentDealRequestStatus = commentDealRequestStatus;
    }

    public DealRequestStatus getDealRequestStatus() {
        return dealRequestStatus == null ? null : DealRequestStatus.fromId(dealRequestStatus);
    }

    public void setDealRequestStatus(DealRequestStatus dealRequestStatus) {
        this.dealRequestStatus = dealRequestStatus == null ? null : dealRequestStatus.getId();
    }

    public AgriculturalManufacturer getProductSeller() {
        return productSeller;
    }

    public void setProductSeller(AgriculturalManufacturer productSeller) {
        this.productSeller = productSeller;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getTermsPayment() {
        return termsPayment;
    }

    public void setTermsPayment(String termsPayment) {
        this.termsPayment = termsPayment;
    }

    public String getDeliveryTerms() {
        return deliveryTerms;
    }

    public void setDeliveryTerms(String deliveryTerms) {
        this.deliveryTerms = deliveryTerms;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getProposalCost() {
        return proposalCost;
    }

    public void setProposalCost(BigDecimal proposalCost) {
        this.proposalCost = proposalCost;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public ProductByer getProductBuyer() {
        return productBuyer;
    }

    public void setProductBuyer(ProductByer productBuyer) {
        this.productBuyer = productBuyer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}