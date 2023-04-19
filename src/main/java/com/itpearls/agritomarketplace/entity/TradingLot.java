package com.itpearls.agritomarketplace.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "TRADING_LOT", indexes = {
        @Index(name = "IDX_TRADING_LOT_AGRICULTURAL_MANUFACTURER", columnList = "AGRICULTURAL_MANUFACTURER_ID"),
        @Index(name = "IDX_TRADING_LOT_PRODUCT", columnList = "PRODUCT_ID"),
        @Index(name = "IDX_TRADING_LOT_UNIT_MEASURMENT", columnList = "UNIT_MEASURMENT_ID"),
        @Index(name = "IDX_TRADING_LOT_PRODUCT_GRADE", columnList = "PRODUCT_GRADE_ID"),
        @Index(name = "IDX_TRADING_LOT_ARTICLE_UNIQUE", columnList = "LOT_ARTICLE"),
        @Index(name = "IDX_TRADING_LOT_PRODUCT_BUYER", columnList = "PRODUCT_BUYER_ID")
})
@Entity
public class TradingLot {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "LOT_ARTICLE", nullable = false, length = 16)
    @NotNull
    private String lotArticle;

    @NotNull
    @JoinColumn(name = "AGRICULTURAL_MANUFACTURER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Counterparty agriculturalManufacturer;

    @JoinColumn(name = "PRODUCT_BUYER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Counterparty productBuyer;

    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Product product;

    @JoinColumn(name = "PRODUCT_GRADE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ProductGrade productGrade;

    @NotNull
    @Column(name = "PRODUCT_AMOUNT", nullable = false)
    private BigDecimal productAmount;

    @JoinColumn(name = "UNIT_MEASURMENT_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private UnitMeasurment unitMeasurment;

    @NotNull
    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "COMMENT_")
    @Lob
    private String comment;

    @Composition
    @OneToMany(mappedBy = "tradingLot")
    private List<Bidding> bidding;

    public void setProductBuyer(Counterparty productBuyer) {
        this.productBuyer = productBuyer;
    }

    public Counterparty getProductBuyer() {
        return productBuyer;
    }

    public List<Bidding> getBidding() {
        return bidding;
    }

    public void setBidding(List<Bidding> bidding) {
        this.bidding = bidding;
    }

    public void setAgriculturalManufacturer(Counterparty agriculturalManufacturer) {
        this.agriculturalManufacturer = agriculturalManufacturer;
    }

    public Counterparty getAgriculturalManufacturer() {
        return agriculturalManufacturer;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setProductAmount(BigDecimal productAmount) {
        this.productAmount = productAmount;
    }

    public BigDecimal getProductAmount() {
        return productAmount;
    }

    public ProductGrade getProductGrade() {
        return productGrade;
    }

    public void setProductGrade(ProductGrade productGrade) {
        this.productGrade = productGrade;
    }

    public String getLotArticle() {
        return lotArticle;
    }

    public void setLotArticle(String lotArticle) {
        this.lotArticle = lotArticle;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UnitMeasurment getUnitMeasurment() {
        return unitMeasurment;
    }

    public void setUnitMeasurment(UnitMeasurment unitMeasurment) {
        this.unitMeasurment = unitMeasurment;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"lotArticle"})
    public String getInstanceName() {
        return String.format("%s", lotArticle);
    }
}